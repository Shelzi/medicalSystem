package com.epam.medicalsystem.controller.tag;

import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.model.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class PermissionTag extends TagSupport {
    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        UserRole currentRole = (UserRole) session.getAttribute(SessionAttribute.CURRENT_ROLE);
        if (currentRole != null && currentRole != UserRole.GUEST) {
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }
}
