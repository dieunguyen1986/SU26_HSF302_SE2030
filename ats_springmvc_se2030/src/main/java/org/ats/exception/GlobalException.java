package org.ats.exception;

import jakarta.persistence.NoResultException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoResultException.class)
    public String noResult(NoResultException ex, Model model) {
        model.addAttribute("error", "Wrong email or password");
        return "login";
    }
}
