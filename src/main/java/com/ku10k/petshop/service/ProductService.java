package com.ku10k.petshop.service;

import com.ku10k.petshop.exceptions.ProductNotFoundException;
import com.ku10k.petshop.facades.ImageFacade;
import com.ku10k.petshop.models.Image;
import com.ku10k.petshop.models.Product;

import com.ku10k.petshop.models.User;
import com.ku10k.petshop.repositories.ImageRepository;
import com.ku10k.petshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageFacade imageFacade;
    private final ImageRepository imageRepository;

    private static byte[] decompressBytes(byte[] data) throws DataFormatException {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.error("Cannot compress Bytes");
        }


        return outputStream.toByteArray();
    }
    public List<Product> getAllProducts( String searchCity,String searchWord) {
        List<Product> products = productRepository.findAll();


        if (!searchWord.equals("")) products = searchBySearchWord(searchWord, products);

        if (!searchCity.equals("")) products.removeIf(m -> !m.getCity().equals(searchCity));

        if (!searchWord.equals("") && !searchCity.equals("")) return products;


        log.info("Get all products with search word: {} search city: {}", searchWord, searchCity);
        return products;
    }
    public Product getById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            log.info("Get product with id {}", id);
            return optionalProduct.get();
        } else {
            log.warn("Product with id {} not found", id);
            throw new ProductNotFoundException("Product with id " + id + " isn't found");
        }
    }


    public void save(User user, Product product, MultipartFile image) throws IOException {
        product.setUser(user);

        Image imageModel = imageFacade.toEntity(image);
        imageModel.setBytes(compressBytes(image.getBytes()));
        product.setImage(imageModel);

        log.info("Saving new Product with title {}", product.getTitle());
        productRepository.save(product);

    }

    public void delete(Long id, User user) {
        Product product = getById(id);
        if (product.getUser().getId().equals(user.getId())) {
            log.info("Product with id {} was deleted", id);
            productRepository.delete(product);
        } else {
            log.warn("User {} haven't this product with id {}", user.getEmail(), id);
        }
    }

    public List<Product> getProductsByUserId(Long userId) {
        return productRepository.getProductsByUserId(userId);
    }

    public Image getImageById(Long id) throws DataFormatException {
        Image image = imageRepository.findById(id)
                .orElse(null);
        image.setBytes(decompressBytes(image.getBytes()));
        return image;
    }

    private List<Product> searchBySearchWord(String searchWord, List<Product> products) {
        List<Product> searchProducts = new ArrayList<>();
        String lowerSearchWord = makeStringToLowerCase(searchWord);
        char[] searchWordToCharArray = lowerSearchWord.toCharArray();
        for (int a = 0; a < products.size(); a++) {
            String lowerProductName = makeStringToLowerCase(products.get(a).getTitle());
            char[] chars = lowerProductName.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < searchWordToCharArray.length; j++) {
                    try {
                        if (chars[i] == searchWordToCharArray[j]) {
                            if (chars[i + 1] == searchWordToCharArray[j + 1]) {
                                if (chars[i + 2] == searchWordToCharArray[j + 2]) {
                                    if (chars[i + 3] == searchWordToCharArray[j + 3]) {
                                        if (!searchProducts.contains(products.get(a))) {
                                            searchProducts.add(products.get(a));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        break;
                    }
                }
            }
        }
        return searchProducts;
    }


    private String makeStringToLowerCase(String word) {
        String loweString = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            loweString += Character.toLowerCase(c);
        }
        return loweString;
    }





    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - "
                + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }


}


