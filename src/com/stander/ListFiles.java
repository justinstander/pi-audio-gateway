package com.stander;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class AudioGateway
 */
@WebServlet(description = "Audio file listing", urlPatterns = { "/ListFiles" })
public final class ListFiles extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(ListFiles.class);
	private static final long serialVersionUID = 1L;
	private static Model model;

    /**
     * Default constructor. 
     */
    public ListFiles() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		logger.info("init");
		model = Model.getInstance();
		logger.info("Directories: ");
		logger.info(model.getArtists().toString());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		logger.info("destroy");
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		logger.info("AudioGateway#getServletConfig");
		return super.getServletConfig();
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		logger.info("AudioGateway#getServletInfo");
		return super.getServletInfo(); 
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(	
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("service");
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doPost");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doPut");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doDelete");
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doHead");
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doOptions");
	}

	/**
	 * @see HttpServlet#doTrace(HttpServletRequest, HttpServletResponse)
	 */
	protected void doTrace(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("AudioGateway#doOptions");
	}

}
