package server;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import servlet.SvrLimitHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 2019/6/29.
 */
public class SysLogServlet extends SvrLimitHttpServlet {
    static final Logger logger = LoggerFactory.getLogger(SysLogServlet.class);

    @Override
    public void processRequest(HttpServlet svrlet, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String logStr = request.getParameter("logStr");
        if(logStr==null || logStr.length()==0){
            return;
        }

        logger.error(logStr);
    }
}
