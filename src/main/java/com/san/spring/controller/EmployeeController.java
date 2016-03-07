package com.san.spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.san.spring.DTO.EmployeeDTO;
import com.san.spring.DTO.SearchDTO;
import com.san.spring.exception.EmployeeNotFoundException;
import com.san.spring.exception.ResourceNotFoundException;
import com.san.spring.service.EmployeeRepositoryService;

@Controller
@SessionAttributes("employee")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	protected static final String ERROR_MESSAGE_KEY_DELETED_EMPLOYEE_WAS_NOT_FOUND = "error.message.deleted.not.found";
	protected static final String ERROR_MESSAGE_KEY_EDITED_EMPLOYEE_WAS_NOT_FOUND = "error.message.edited.not.found";

	protected static final String FEEDBACK_MESSAGE_KEY_EMPLOYEE_CREATED = "feedback.message.employee.created";
	protected static final String FEEDBACK_MESSAGE_KEY_EMPLOYEE_DELETED = "feedback.message.employee.deleted";
	protected static final String FEEDBACK_MESSAGE_KEY_EMPLOYEE_EDITED = "feedback.message.employee.edited";

	protected static final String MODEL_ATTIRUTE_EMPLOYEE = "employee";
	protected static final String MODEL_ATTRIBUTE_EMPLOYEES = "employees";
	protected static final String MODEL_ATTRIBUTE_SEARCH_CRITERIA = "searchCriteria";

	protected static final String EMPLOYEE_ADD_FORM_VIEW = "employee/create";
	protected static final String EMPLOYEE_EDIT_FORM_VIEW = "employee/edit";
	protected static final String EMPLOYEE_LIST_VIEW = "employee/list";
	protected static final String EMPLOYEE_SEARCH_RESULT_VIEW = "employee/searchResults";

	protected static final String REQUEST_MAPPING_LIST = "/";

	@Autowired
	private EmployeeRepositoryService employeeService;

	@RequestMapping(value = "/", method = RequestMethod.GET) 
	public ModelAndView helloWorldOne() {
		LOGGER.info("Inside helloWorldOne ");
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET) 
	public ModelAndView viewEmployee() {
		LOGGER.info("Inside viewEmployee ");
		return new ModelAndView("list");
	}

	
	/*@RequestMapping(value = "/employee/count", method = RequestMethod.POST)
	 * @ResponseBody public Long count(@RequestBody SearchDTO searchCriteria) {
	 * String searchTerm = searchCriteria.getSearchTerm();
	 * 
	 * LOGGER.debug("Finding employee count for search term: " + searchTerm);
	 * 
	 * return employeeService. count(searchTerm); }
	 * 
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView handleResourceNotFoundException() {
		return new ModelAndView("notfound");
	}

	/*
	 * @RequestMapping(value = "/employee/delete/{id}", method =
	 * RequestMethod.GET) public String delete(@PathVariable("id") Long id,
	 * RedirectAttributes attributes) { LOGGER.debug(
	 * "Deleting employee with id: " + id); try { EmployeeDTO deleted =
	 * employeeService.delete(id); addFeedbackMessage(attributes,
	 * FEEDBACK_MESSAGE_KEY_EMPLOYEE_DELETED, deleted.getfName()); } catch
	 * (EmployeeNotFoundException e) { LOGGER.debug(
	 * "No employee found with id: " + id); addErrorMessage(attributes,
	 * ERROR_MESSAGE_KEY_DELETED_EMPLOYEE_WAS_NOT_FOUND); } return
	 * createRedirectViewPath(REQUEST_MAPPING_LIST); }
	 */

	/*
	 * private List<PersonDTO> constructDTOs(List<employee> persons) {
	 * List<PersonDTO> dtos = new ArrayList<PersonDTO>();
	 * 
	 * for (employee employee: persons) { PersonDTO dto = new PersonDTO();
	 * dto.setId(employee.getId()); dto.setFirstName(employee.getFirstName());
	 * dto.setLastName(employee.getLastName());
	 * 
	 * dtos.add(dto); }
	 * 
	 * return dtos; }
	 * 
	 * @RequestMapping(value = "/employee/create", method = RequestMethod.GET)
	 * public String showCreatePersonForm(Model model) { LOGGER.debug(
	 * "Rendering create employee form");
	 * 
	 * model.addAttribute(MODEL_ATTIRUTE_EMPLOYEE, new PersonDTO());
	 * 
	 * return EMPLOYEE_ADD_FORM_VIEW; }
	 * 
	 * @RequestMapping(value = "/employee/create", method = RequestMethod.POST)
	 * public String
	 * submitCreatePersonForm(@Valid @ModelAttribute(MODEL_ATTIRUTE_EMPLOYEE)
	 * PersonDTO created, BindingResult bindingResult, RedirectAttributes
	 * attributes) { LOGGER.debug(
	 * "Create employee form was submitted with information: " + created);
	 * 
	 * if (bindingResult.hasErrors()) { return EMPLOYEE_ADD_FORM_VIEW; }
	 * 
	 * employee employee = personService.create(created);
	 * 
	 * addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EMPLOYEE_CREATED,
	 * employee.getName());
	 * 
	 * return createRedirectViewPath(REQUEST_MAPPING_LIST); }
	 * 
	 * @RequestMapping(value = "/employee/edit/{id}", method =
	 * RequestMethod.GET) public String showEditPersonForm(@PathVariable("id")
	 * Long id, Model model, RedirectAttributes attributes) { LOGGER.debug(
	 * "Rendering edit employee form for employee with id: " + id);
	 * 
	 * employee employee = personService.findById(id); if (employee == null) {
	 * LOGGER.debug("No employee found with id: " + id);
	 * addErrorMessage(attributes,
	 * ERROR_MESSAGE_KEY_EDITED_EMPLOYEE_WAS_NOT_FOUND); return
	 * createRedirectViewPath(REQUEST_MAPPING_LIST); }
	 * 
	 * model.addAttribute(MODEL_ATTIRUTE_EMPLOYEE,
	 * constructFormObject(employee));
	 * 
	 * return EMPLOYEE_EDIT_FORM_VIEW; }
	 * 
	 * @RequestMapping(value = "/employee/edit", method = RequestMethod.POST)
	 * public String
	 * submitEditPersonForm(@Valid @ModelAttribute(MODEL_ATTIRUTE_EMPLOYEE)
	 * PersonDTO updated, BindingResult bindingResult, RedirectAttributes
	 * attributes) { LOGGER.debug(
	 * "Edit employee form was submitted with information: " + updated);
	 * 
	 * if (bindingResult.hasErrors()) { LOGGER.debug(
	 * "Edit employee form contains validation errors. Rendering form view.");
	 * return EMPLOYEE_EDIT_FORM_VIEW; }
	 * 
	 * try { employee employee = personService.update(updated);
	 * addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_EMPLOYEE_EDITED,
	 * employee.getName()); } catch (PersonNotFoundException e) { LOGGER.debug(
	 * "No employee was found with id: " + updated.getId());
	 * addErrorMessage(attributes,
	 * ERROR_MESSAGE_KEY_EDITED_EMPLOYEE_WAS_NOT_FOUND); }
	 * 
	 * return createRedirectViewPath(REQUEST_MAPPING_LIST); }
	 * 
	 * private PersonDTO constructFormObject(employee employee) { PersonDTO
	 * formObject = new PersonDTO();
	 * 
	 * formObject.setId(employee.getId());
	 * formObject.setFirstName(employee.getFirstName());
	 * formObject.setLastName(employee.getLastName());
	 * 
	 * return formObject; }
	 */

	@RequestMapping(value = REQUEST_MAPPING_LIST, method = RequestMethod.GET)
	public String showList(Model model) {
		LOGGER.debug("Rendering employee list page");

		List<EmployeeDTO> employees = employeeService.findAll();
		model.addAttribute(MODEL_ATTRIBUTE_EMPLOYEES, employees);
		model.addAttribute(MODEL_ATTRIBUTE_SEARCH_CRITERIA, new SearchDTO());

		return EMPLOYEE_LIST_VIEW;
	}

	@RequestMapping(value = "/employee/search", method = RequestMethod.POST)
	public String showSearchResultPage(@ModelAttribute(MODEL_ATTRIBUTE_SEARCH_CRITERIA) SearchDTO searchCriteria,
			Model model) {
		LOGGER.debug("Rendering search result page for search criteria: " + searchCriteria);

		model.addAttribute(MODEL_ATTRIBUTE_SEARCH_CRITERIA, searchCriteria);

		return EMPLOYEE_SEARCH_RESULT_VIEW;
	}
}
