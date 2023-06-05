package com.ku10k.petshop.service;

import com.ku10k.petshop.models.Product;
import com.ku10k.petshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        log.info("get all products");
        return productRepository.findAll();
    }

    public void save(Product product) {
        log.info("save product {}", product);
        productRepository.save(product);
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
}



//    public List<Product> findByTitle(String title) {
//        log.info("get title products");
//        return productRepository.findByTitle(title);
//    }