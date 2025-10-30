package mk.ukim.finki.wp.lab.web;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookReservationService bookReservationService;

    public BookReservationServlet(SpringTemplateEngine springTemplateEngine, BookReservationService bookReservationService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookReservationService = bookReservationService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);



        String title = request.getParameter("title");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int numCopies = Integer.parseInt(request.getParameter("numCopies"));
        BookReservation reservation = bookReservationService.placeReservation(
                title, name, address, numCopies
        );

        String clientIp = request.getRemoteAddr();
        context.setVariable("clientIp", clientIp);

        context.setVariable("name", reservation.getReaderName());
        context.setVariable("title", reservation.getBookTitle());
        context.setVariable("numCopies", reservation.getNumberOfCopies());

        springTemplateEngine.process("reservationConfirmation.html", context, response.getWriter());
    }

}