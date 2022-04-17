package com.epam.medicalsystem.controller.filter;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandProvider;
import com.epam.medicalsystem.controller.command.CommandType;
import com.epam.medicalsystem.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PermissionFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final Map<UserRole, EnumSet<CommandType>> permissionCommands = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        EnumSet<CommandType> sameCommands = EnumSet.of(CommandType.CHANGE_LANGUAGE);

        EnumSet<CommandType> guestCommands = EnumSet.of(CommandType.REGISTER, CommandType.LOGIN);
        guestCommands.addAll(sameCommands);

        EnumSet<CommandType> adminCommands = EnumSet.of(CommandType.LOGOUT);
        adminCommands.addAll(sameCommands);

        EnumSet<CommandType> doctorCommands = EnumSet.of(CommandType.LOGOUT, CommandType.VIEW_ALL_PATIENTS,
                CommandType.VIEW_ALL_VISITS, CommandType.TO_ADD_VISIT, CommandType.CREATE_VISIT,
                CommandType.FIND_PATIENT, CommandType.TO_FIND_PATIENT);
        doctorCommands.addAll(sameCommands);

        EnumSet<CommandType> nurseCommands = EnumSet.of(CommandType.LOGOUT, CommandType.FIND_PATIENT,
                CommandType.TO_FIND_PATIENT);
        nurseCommands.addAll(sameCommands);

        EnumSet<CommandType> labmemCommands = EnumSet.of(CommandType.LOGOUT);
        labmemCommands.addAll(sameCommands);

        EnumSet<CommandType> registrarCommands = EnumSet.of(CommandType.LOGOUT, CommandType.TO_ADD_PATIENT,
                CommandType.CREATE_PATIENT);
        registrarCommands.addAll(sameCommands);

        permissionCommands.put(UserRole.GUEST, guestCommands);
        permissionCommands.put(UserRole.ADMIN, adminCommands);
        permissionCommands.put(UserRole.DOCTOR, doctorCommands);
        permissionCommands.put(UserRole.NURSE, nurseCommands);
        permissionCommands.put(UserRole.LABMEM, labmemCommands);
        permissionCommands.put(UserRole.REGISTRAR, registrarCommands);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        UserRole role = (UserRole) session.getAttribute(SessionAttribute.CURRENT_ROLE);
        Optional<ActionCommand> optionalCommand = CommandProvider.defineCommand(request);
        if (!optionalCommand.isPresent()) {
            chain.doFilter(request, response);
            return;
        }
        EnumSet<CommandType> commands = permissionCommands.get(role);
        Optional<CommandType> command = CommandProvider.defineCommandType(request);
        if (commands == null || !command.isPresent() || !commands.contains(command.get())) {
            logger.log(Level.ERROR, "User hasn't got permission to execute command = " + command);
            RequestDispatcher dispatcher = request.getRequestDispatcher(PagePath.MAIN);
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}