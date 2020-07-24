package com.spring5.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring5.model.MailUser;
import com.spring5.model.MailUserManagement;
import com.spring5.model.users.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import com.spring5.service.MailUserService;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

// http://learningprogramming.net/java/spring-mvc/pagination-with-spring-data-jpa-in-spring-mvc/
// https://examples.javacodegeeks.com/enterprise-java/spring/mvc/spring-mvc-pagination-example/
// https://examples.javacodegeeks.com/enterprise-java/spring/data/spring-data-jparepository-example/
//
@Controller
//@RequestMapping("/mailusers")
public class MailUserController {

    private static final Logger log = LoggerFactory.getLogger(MailUserController.class);

    @Autowired
    private MailUserService mailUserService;
    @Autowired
    private MailUserManagement userManagement;

    private List<MailUser> users;

    @PostConstruct
    public void init() {
        users = createMailUsers();
    }

    /**
     * Equips the model with a {@link Page} of {@link User}s. Spring Data
     * automatically populates the {@link Pageable} from request data according
     * to the setup of {@link PageableHandlerMethodArgumentResolver}. Note how
     * the defaults can be tweaked by using {@link PageableDefault}.
     *
     * @param pageable will never be {@literal null}.
     * @return
     */
    @ModelAttribute("mailusers")
    public Page<MailUser> mailusers(@PageableDefault(size = 5) Pageable pageable) {
        log.debug("users pageable {}", pageable);
        return mailUserService.findAll(pageable);
    }

    /**
     * Populates the {@link Model} with the {@link UserForm} automatically
     * created by Spring Data web components. It will create a
     * {@link Map}-backed proxy for the interface.
     *
     * @param model will never be {@literal null}.
     * @param userForm will never be {@literal null}.
     * @return
     */
    @RequestMapping(value = "/mailusers", method = RequestMethod.GET)
    public String mailUsers(Model model, MailUserForm userForm) {
        log.debug("mailusers userForm {}", userForm);
        model.addAttribute("userForm", userForm);

        return "mailusers";
    }

    /**
     * Registers a new {@link User} for the data provided by the given
     * {@link UserForm}. Note, how an interface is used to bind request
     * parameters.
     *
     * @param userForm the request data bound to the {@link UserForm} instance.
     * @param binding the result of the binding operation.
     * @param model the Spring MVC {@link Model}.
     * @return
     */
    @RequestMapping(value = "/registermailuser", method = RequestMethod.POST)
    public Object registerMailUser(MailUserForm userForm, BindingResult binding, Model model) {

        userForm.validate(binding, userManagement);

        if (binding.hasErrors()) {
            return "mailusers";
        }

        userManagement.register(userForm.getName(), userForm.getEmail());

        RedirectView redirectView = new RedirectView("redirect:/mailusers");
        redirectView.setPropagateQueryParams(true);

        return redirectView;
    }

    /**
     * An interface to represent the form to be used
     *
     * @author Oliver Gierke
     */
    interface MailUserForm {

        String getName();

        String getEmail();

        /**
         * Validates the {@link UserForm}.
         *
         * @param errors
         * @param userManagement
         */
        @Autowired
        default void validate(BindingResult errors, MailUserManagement userManagement) {

            rejectIfEmptyOrWhitespace(errors, "name", "user.name.empty");
            rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");
            try {
                userManagement.findByUsername(getName()).ifPresent(
                        user -> errors.rejectValue("name", "user.name.exists"));
            } catch (IllegalArgumentException o_O) {
                errors.rejectValue("name", "user.name.invalidFormat");
            }
        }
    }

    @GetMapping("/editUsers")
    public String userForm(Locale locale, Model model) {
        model.addAttribute("users", mailUserService.findAll());
        return "editUsers";
    }

    @ModelAttribute("user")
    public MailUser formBackingObject() {
        return new MailUser();
    }

    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid MailUser user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("users", mailUserService.findAll());
            return "editUsers";
        }

        mailUserService.save(user);
        return "redirect:/";
    }

    @GetMapping("/listUsers")
    public String listUsers(Locale locale, Model model, HttpServletRequest request, ModelMap modelMap) {
        System.out.println("listUsers");
        //List<User> users = userService.listUsers(); //userPagingRepositary.findAll();
        PagedListHolder pagedListHolder = new PagedListHolder(users);
        long count = users.size();
        int pageSize = 5;
        long pages = count / pageSize;
        System.out.println("total " + count + "-pages=" + pages);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(3);
        modelMap.put("pagedListHolder", pagedListHolder);
        //model.addAttribute("users", userService.listUsers());
        return "listUsers";
    }

    private List<MailUser> createMailUsers() {

        List<MailUser> returnValue = new ArrayList();
        MailUser user = null;
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            user = new MailUser();
            user.setName(F_N_LIST.get(rand.nextInt(F_N_LIST.size()))
                    + " " + L_N_LIST.get(rand.nextInt(L_N_LIST.size())));
            user.setEmail(getAlphaNumericString(5) + E_LIST.get(rand.nextInt(E_LIST.size())));

            mailUserService.save(user);
            returnValue.add(user);
        }

        //userPagingRepositary.saveAll(returnValue);
        //userCrudRepo.saveAll(returnValue);
        return returnValue;
    }

    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    static final String[] FIRST_NAMES = {"Alex", "Arby", "Allen", "Abbey", "Ashley", "Ben", "Bill", "Carol", "Dan", "Don", "Doug", "Ernie", "Gary", "Jax", "Jon", "Jeff", "Jessica", "Kevin", "Shannon"};
    static final String[] LAST_NAMES = {"Alton", "Aleon", "Atux", "Lee", "Swift", "Liu", "Alexon", "Alatian", "Smith", "Smita", "Will", "Wall", "Zina"};
    static final String[] EMAILS = {"aaa@ciminc.com", "a12@ciminc.com", "a2a@ciminc.com", "alibaba@ciminc.com", "alab@ciminc.com", "alex@ciminc.com",
        "arby@ciminc.com", "Allen@ciminc.com", "Abbey@ciminc.com", "Ashley@ciminc.com", "Ben@ciminc.com", "Bill@ciminc.com", "Carol@ciminc.com",
        "Dan@ciminc.com", "Don@ciminc.com", "Doug@ciminc.com", "Ernie@ciminc.com", "Gary@ciminc.com", "Jax@ciminc.com", "Jon@ciminc.com",
        "Jeff@ciminc.com", "Jessica@ciminc.com", "Kevin@ciminc.com", "Shannon@ciminc.com"};
    static final List<String> F_N_LIST = Arrays.asList(FIRST_NAMES);
    static final List<String> L_N_LIST = Arrays.asList(LAST_NAMES);
    static final List<String> E_LIST = Arrays.asList(EMAILS);
}
