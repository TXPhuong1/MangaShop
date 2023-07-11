package edu.poly.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.poly.repository.AccountRepository;
import edu.poly.repository.CategoryRepository;
import edu.poly.repository.OrderRepository;
import edu.poly.repository.ProductRepository;
import edu.poly.entity.Account;
import edu.poly.entity.Category;
import edu.poly.entity.Order;
import edu.poly.entity.Product;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.MultipartConfig;

@Controller
@MultipartConfig
public class AdminController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ServletContext app;

    @GetMapping("/admin/user")
    public String user(Model model, @ModelAttribute("userEdit") Account account, @RequestParam("name") Optional<String> name) {
        model.addAttribute("accountList", accountRepository.findAllByFullnameLike("%" + name.orElse("") + "%"));
        model.addAttribute("name", name.orElse(""));
        return "admin/user-list";
    }

    @GetMapping("/admin/user/delete/{username}")
    public String user_delete(@ModelAttribute("userEdit") Account account, @PathVariable("username") String username, Model model) {
        if (accountRepository.existsById(username)) {
            Account acc = accountRepository.findById(username).get();
            acc.setActivated(false);
            accountRepository.save(acc);
            model.addAttribute("success_user_delete", true);
        } else
            model.addAttribute("error_user_delete", true);
        return "admin/user-list";
    }

    @PostMapping("/admin/user/edit")
    public String user_update(@Validated @ModelAttribute("userEdit") Account account, BindingResult result, Model model, @RequestParam("image") Optional<MultipartFile> file) {
        if (!accountRepository.existsById(account.getUsername()))
            model.addAttribute("error_user_update", true);
        else {
            if (!file.get().getOriginalFilename().equals("")) {
                String fileName = StringUtils.cleanPath(file.orElse(null).getOriginalFilename());
                // save the file on the local file system
                try {
                    Path path = Paths.get(app.getRealPath("/assets/img/avatars/" + fileName));
                    Files.copy(file.orElse(null).getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    account.setPhoto(fileName);
                    accountRepository.save(account);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                account.setPhoto(accountRepository.findById(account.getUsername()).get().getPhoto());
                accountRepository.save(account);
            }
            model.addAttribute("success_user_update", true);
        }
        return "admin/user-list";
    }

    @GetMapping("/admin/product")
    public String product(Model model, @ModelAttribute("productEdit") Product product, @RequestParam("name") Optional<String> name) {
        model.addAttribute("productList", productRepository.findAllBynameLike("%" + name.orElse("") + "%"));
        model.addAttribute("name", name.orElse(""));
        return "admin/product-list";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String product_delete(@ModelAttribute("productEdit") Product product, @PathVariable("id") Integer id, Model model) {
        if (productRepository.existsById(id)) {
            Product pro = productRepository.findById(id).get();
            pro.setIsDelete(true);
            productRepository.save(pro);
            model.addAttribute("success_product_delete", true);
        } else
            model.addAttribute("error_product_delete", true);
        return "admin/product-list";
    }

    @PostMapping("/admin/product/edit")
    public String product_update(@Validated @ModelAttribute("productEdit") Product product, BindingResult result, Model model, @RequestParam("categoryId") String caId) {
        if (!productRepository.existsById(product.getId()))
            model.addAttribute("error_product_update", true);
        else {
            product.setImage(productRepository.findById(product.getId()).get().getImage());
            product.setCategory(categoryRepository.findById(caId).get());
            product.setIsDelete(!product.getIsDelete());
            productRepository.save(product);
            model.addAttribute("success_product_update", true);
        }
        return "admin/product-list";
    }

    @GetMapping("/admin/order")
    public String order(Model model, @RequestParam("id") Optional<Long> id) {
        if (id.orElse((long) 0) != 0) {
            List<Order> list = new ArrayList<>();
            list.add(orderRepository.findById(id.orElse((long) 0)).get());
            model.addAttribute("orderList", list);
        }
        model.addAttribute("id", (id.orElse((long) 0) == 0) ? "" : id.orElse((long) 0));
        return "admin/order-list";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String order_delete(@ModelAttribute("orderEdit") Order order, @PathVariable("id") Long id, Model model) {
        if (orderRepository.existsById(id)) {
            Order o = orderRepository.findById(id).get();
            o.setStatus(true);
            orderRepository.save(o);
            model.addAttribute("success_order_delete", true);
        } else
            model.addAttribute("error_order_delete", true);
        return "admin/order-list";
    }

    @GetMapping("/admin/add-product")
    public String add_product(Model model, @ModelAttribute("productAdd") Product product) {
        return "admin/add-product";
    }

    @PostMapping("/admin/add-product")
    public String add_product_post(Model model, @ModelAttribute("productAdd") Product product, @RequestParam("images") MultipartFile[] files, @RequestParam("categoryId") String categoryId) {
        String imgs = "";
        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            // save the file on the local file system
            try {
                Path path = Paths.get(app.getRealPath("/assets/img/product/" + fileName));
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                imgs += (fileName + ",");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("error_product_add", true);
            }
        }
        product.setCategory(categoryRepository.findById(categoryId).get());
        product.setImage(imgs.substring(0, imgs.length() - 1));
        productRepository.save(product);
        model.addAttribute("success_product_add", true);
        return "admin/add-product";
    }

    //cate
    @GetMapping("/admin/category")
    public String category(Model model, @ModelAttribute("categoryEdit") Category category, @RequestParam("name") Optional<String> name) {
        model.addAttribute("categoryList", categoryRepository.findAllByIdLike("%" + name.orElse("") + "%"));
        model.addAttribute("name", name.orElse(""));
        return "admin/category-list";
    }

    @GetMapping("/admin/add-category")
    public String add_category(Model model, @ModelAttribute("categoryAdd") Category category) {
        return "admin/add-category";
    }

    @PostMapping("/admin/add-category")
    public String add_category_post(Model model, @ModelAttribute("categoryAdd") Category category) {
        categoryRepository.save(category);
        model.addAttribute("success_category_add", true);
        return "admin/add-category";
    }

    @PostMapping("/admin/category/edit")
    public String category_update(@Validated @ModelAttribute("categoryEdit") Category category, BindingResult result, Model model) {
        if (!categoryRepository.existsById(category.getId())) {
            model.addAttribute("error_category_update", true);
        } else {
            categoryRepository.save(category);
        }
        model.addAttribute("success_category_update", true);
        return "admin/category-list";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String category_delete(@ModelAttribute("categoryEdit") Category category, @PathVariable("id") String id, Model model) {
        if (categoryRepository.existsById(id)) {
            Category c = categoryRepository.findById(id).get();
            c.setIsDelete(true);
            categoryRepository.save(c);
            model.addAttribute("success_category_delete", true);
        } else
            model.addAttribute("error_category_delete", true);
        return "admin/category-list";
    }

    @ModelAttribute("productList")
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("accountList")
    public List<Account> accountList() {
        return accountRepository.findAll();
    }

    @ModelAttribute("orderList")
    public List<Order> orderList() {
        return orderRepository.findAll();
    }

    @ModelAttribute("categoryList")
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }
}
