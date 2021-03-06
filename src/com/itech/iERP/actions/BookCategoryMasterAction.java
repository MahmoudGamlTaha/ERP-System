package com.itech.iERP.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itech.iERP.forms.BookCatForm;
import com.itech.iERP.forms.ContryForm;
import com.itech.iERP.handler.BookCategoryHandler;

public class BookCategoryMasterAction 	extends BaseAction{

		BookCategoryHandler handler = new BookCategoryHandler();
		
		 public ActionForward list(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
		  {
			  
			  List<BookCatForm> bookcatlist = handler.list(getDataSource(request));
			  request.setAttribute("bookcatlist",bookcatlist);
			  return mapping.findForward("Bookcat");
		  }
		  
		  public ActionForward add(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
		  {
			  
			  BookCatForm bookcatform = (BookCatForm)form;
			  HttpSession session=request.getSession();
			  
			  if(session.getAttribute("masterData")!=null)
				{
				   long access= (Long) session.getAttribute("masterData");
				   if(access==1)
				   {
			    String status = handler.add(bookcatform,getDataSource(request));
			    request.setAttribute("result", status);
				   }
			    else 
			    	 request.setAttribute("status", "You are not authorised to add");	
				   }
			
			  return list(mapping, form, request, response);
		  }
		  
		 
		  
		  public ActionForward update(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
		  {
			  BookCatForm bookcatform = (BookCatForm)form;
			  HttpSession session=request.getSession();
			  
			     if(session.getAttribute("masterData")!=null)
					{
					   long access= (Long) session.getAttribute("masterData");
						if(access==1)
			  request.setAttribute("status", handler.update(bookcatform,getDataSource(request)));
						else
							request.setAttribute("status", "You are not authorised to update ");	
					}
			    
						return list(mapping, form, request, response);
		  }
		  
		  
		  
		  public ActionForward changestatus(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
		  {
			  BookCatForm bookcatform = (BookCatForm)form;
			  bookcatform.setBookcatid(Integer.parseInt(request.getParameter("bookcatid").trim()));
			  bookcatform.setActive(Boolean.parseBoolean(request.getParameter("active")));
			  HttpSession session=request.getSession();
				if(session.getAttribute("masterData")!=null)
				{
				   long access= (Long) session.getAttribute("masterData");
					if(access==1)
			  request.setAttribute("status",handler.changeRole(bookcatform,getDataSource(request)));
					else
						request.setAttribute("status", "You are not authorised to change the status");	
				}
			  return list(mapping, form, request, response);
		  }
		  
	}





