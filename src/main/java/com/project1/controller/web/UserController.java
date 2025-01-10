package com.project1.controller.web;
import com.project1.entity.enums.group;
import com.project1.entity.enums.role;
import com.project1.model.dto.UserDTO;
import com.project1.model.request.UserSearchRequest;
import com.project1.model.response.UserSearchResponse;
import com.project1.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("login")
    public String login() {
        return "index";
    }

    @GetMapping("admin/list-account")
    public ModelAndView accountManager(@ModelAttribute UserSearchRequest userSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/account_manager");
        mav.addObject("userSearchRequest", userSearchRequest);
        List<UserSearchResponse> list = userService.findAll(userSearchRequest, PageRequest.of(userSearchRequest.getPage() - 1,userSearchRequest.getMaxPageItems()));
        userSearchRequest.setListResult(list);
        userSearchRequest.setTotalItems(userService.coutTotalItems(userSearchRequest));
        if (userSearchRequest.getTotalItems() % userSearchRequest.getMaxPageItems() == 0) {
            userSearchRequest.setTotalPage(userSearchRequest.getTotalItems() / userSearchRequest.getMaxPageItems());
        } else {
            userSearchRequest.setTotalPage(userSearchRequest.getTotalItems() / userSearchRequest.getMaxPageItems() + 1);
        }
        mav.addObject("roles", role.getRole());
        mav.addObject("list", list);
        return mav;
    }


    @GetMapping("admin/edit-account")
    public ModelAndView editAccount(@ModelAttribute("accountEdit") UserDTO userDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit-account");
        mav.addObject("accountEdit", userDTO);
        mav.addObject("roles", role.getRole());
        mav.addObject("listGroup", group.type());
        return mav;
    }

    @GetMapping("admin/edit-account-{id}")
    public ModelAndView editAccount(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit-account");
        UserDTO userDTO = userService.findById(id);
        mav.addObject("accountEdit", userDTO);
        mav.addObject("roles", role.getRole());
        mav.addObject("listGroup", group.type());
        return mav;
    }
}
