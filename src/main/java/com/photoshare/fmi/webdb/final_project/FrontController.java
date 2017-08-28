/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.photoshare.fmi.webdb.final_project;

import com.photoshare.fmi.webdb.final_project.annotations.Controller;
import com.photoshare.fmi.webdb.final_project.annotations.RequestMapping;
import com.photoshare.fmi.webdb.final_project.controller.HomeController;
import com.photoshare.fmi.webdb.final_project.controller.IController;

import com.photoshare.fmi.webdb.final_project.views.View;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

/**
 *
 * @author killer
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Map<String, IController> controllers = Collections.synchronizedMap(new HashMap<String, IController>());
    private final String controllerPackage = "com.photoshare.fmi.webdb.final_project.controller";
    private ServletContext context;

    public static volatile String DIR_LOCATION;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //To change body of generated methods, choose Tools | Templates.
        context = config.getServletContext();
        
        //Init storage location if needed
        DIR_LOCATION = config.getServletContext().getRealPath("/files/");
        File fileSaveDir = new File(DIR_LOCATION);

        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        
        //Init controller map
        Reflections reflections = new Reflections(controllerPackage);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class currentClass : classes) {
            Controller annotation = (Controller) currentClass.getAnnotation(Controller.class);
            try {
                IController controller = getController(currentClass);
                controllers.put(annotation.value().substring(1), controller);
            } catch (Exception ex) {
                Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getServletPath(); // /{value}/test
        String extraParams = request.getParameter("extraParams");
        Map<String, Object> extractedParameters = null;
        if (extraParams != null) {
            extractedParameters = extractParams(extraParams);
        }

        String controller = request.getParameter("controller");
        String req_method = request.getParameter("method");
        IController ctrl = null;
        Method method;
        if (controller == null && req_method == null) {
            ctrl = controllers.get("");
            method = getMethod(ctrl, "home");
        } else {
            ctrl = controllers.get(controller);
            if (ctrl == null) {
                ctrl = controllers.get("");
                method = getMethod(ctrl, controller);
                if (method == null) {
                    method = getMethod(ctrl, "send404");
                }
            } else {
                method = getMethod(ctrl, req_method);
                if (method == null) {
                    ctrl = controllers.get("");
                    method = getMethod(ctrl, "send404");
                }
            }
        }

        if (extractedParameters != null) {
            for (Map.Entry<String, Object> entry : extractedParameters.entrySet()) {

                request.setAttribute(entry.getKey(), entry.getValue());
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        View result = null;
        try {
            result = (View) method.invoke(ctrl, request, response);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        final RequestDispatcher rd = context.getRequestDispatcher(result.getForward());

        request.setAttribute("uploadPath", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/images/");
        rd.forward(request, response);

    }

    private Method getMethod(IController ctrl, String path) {
        Class ctrlClass = ctrl.getClass();
        for (Method method : ctrlClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping pathValue = (RequestMapping) method.getAnnotation(RequestMapping.class);
                String methodPath = pathValue.value().substring(1);
                if (methodPath.equals(path)) {
                    return method;
                }
            }
        }

        return null;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Map<String, Object> extractParams(String extraParams) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        String parts[] = extraParams.split("/");
        for (int i = 0; i < parts.length; i = i + 2) {
            //$args[$elements[$i]] = $elements[$i + 1];
            map.put(parts[i], parts[i + 1]);
        }
//        for (String keyValue : extraParams.split(" **")) {
//            String[] pairs = keyValue.split(" */ *", 2);
//            map.put(pairs[0], pairs.length == 1 ? "" : pairs[1]);
//        }

        return map;
    }

    private IController getController(Class clz) {
        BeanManager bm = CDI.current().getBeanManager();
//        try {
//            InitialContext context = new InitialContext();
//            bm = (BeanManager) context.lookup("java:comp/env/BeanManager");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Bean<IController> bean = (Bean<IController>) bm.getBeans(clz).iterator().next();
        CreationalContext<IController> ctx = bm.createCreationalContext(bean);
        IController controller = (IController) bm.getReference(bean, clz, ctx);

        return controller;
    }

}
