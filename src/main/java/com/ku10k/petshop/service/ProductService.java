package com.ku10k.petshop.service;

import com.ku10k.petshop.facades.ImageFacade;
import com.ku10k.petshop.models.Image;
import com.ku10k.petshop.models.Product;

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

    public List<Product> getAll() {
        log.info("get all products");
        return productRepository.findAll();
    }

    public void save(Product product, MultipartFile image) throws IOException {

        Image imageModel = imageFacade.toEntity(image);
        imageModel.setBytes(compressBytes(image.getBytes()));
        product.setImage(imageModel);
        log.info("Saving new {}", product.getTitle());
        productRepository.save(product);

    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Image getImageById(Long id) throws DataFormatException {
        Image image = imageRepository.findById(id)
                .orElse(null);
        image.setBytes(decompressBytes(image.getBytes()));
        return image;
    }

    public List<Product> searchByTitle(String searchWord) {
        return searchBySearchWorld(searchWord, productRepository.findAll());
    }

    private List<Product> searchBySearchWorld(String searchWord, List<Product> products) {
        List<Product> searchProduct = new ArrayList<>();
        String loweSearchWord = makeStringToLowerCase(searchWord);
        char[] searchWordToCharArray = loweSearchWord.toCharArray();
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
                                        if (!searchProduct.contains(products.get(a))) {
                                            searchProduct.add(products.get(a));
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
        return searchProduct;
    }


    private String makeStringToLowerCase(String word) {
        String loweString = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            loweString += Character.toLowerCase(c);
        }
        return loweString;
    }

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


//    public List<Product> findByTitle(String title) {
//        log.info("get title products");
//        return productRepository.findByTitle(title);
//    }