package servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Customer;
import model.dao.CustomerDao;
import utils.DataSourceSearcher;

@WebServlet("/customerRegister")
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDao customerDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCustomer() {
        super();
        this.customerDao = new CustomerDao(DataSourceSearcher.getInstance().getDataSource());
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String phone = request.getParameter("phone");
		String cpf = request.getParameter("cpf");
		
		Customer cs = new Customer();
		
		cs.setName(name);
		cs.setMail(mail);
		cs.setPhone(phone);
		cs.setCPF(cpf);
		cs.setIsActive(true);
		
		try {
			customerDao.save(cs);
			request.setAttribute("result", "registered");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
