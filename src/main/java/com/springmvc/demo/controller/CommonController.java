package com.springmvc.demo.controller;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.TaskManager;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/24/2016.
 */

/**
 * <p>Only users with role USER specified in the config.properties file have access to this
 * resource. This controller provides all the common user functionality.</p>
 * <p>Resources</p>
 * <p>{@link #userHome()}
 * <p>{@link #taskDetail(HttpServletRequest, String, String)}</p>
 * <p>{@link #modifyTask(HttpServletRequest, RedirectAttributes)} </p>
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    // region Static Strings
    // Keys for passing resource string names
    public static final String TASK_DETAIL_RESOURCE = "task_detail_resource";
    public static final String TASK_RESOURCES_PATH = "task";

    public static final String HOME = "home";
    public static final String HOME_VALUE = "common";
    public static final String REDIRECT_MODIFY_TO = "redirect_modify_to";
    public static final String BACK = "back";
    public static final String BUTTON_REDIRECTION_PAGE = "button_redirection_page";
    public static final String MODIFY = "modify";
    // JSP's names
    public static final String CREATE_TASK_JSP = "create_task";
    public static final String TASK_DETAIL_JSP = "task_detail";

    // Keys for passing objects
    public static final String USERS = "users";
    public static final String PROJECTS = "projects";

    public static final String USER = "user";
    public static final String USERID = "userId";
    public static final String USER_ID_FOR_USERTD = "userIdForUserTD";

    public static final String PROJECTID = "projectId";

    public static final String TASK = "task";
    public static final String TASKID = "taskId";
    public static final String TASK_STORY = "task_story";
    public static final String TASK_DESCRIPTION = "task_description";

    public static final String MAP = "map";

    // Error handling
    public static final String ERROR = "error";

    // Labels
    public static final String BUTTON_LABEL = "button_label";
    // endregion

    // region Beans
    @Autowired
    ProjectManager projectManager;

    @Autowired
    TaskManager taskManager;

    @Autowired
    UserManager userManager;

    @Autowired
    Environment environment;
    // endregion

    /**
     * Provides a JSP form view to display existing specified user detail.
     * Is invoked by logging in with suitable authorization.
     * @return
     * <p>ModelAndView object with JSP view named "user_detail" under {@link #userHome()} key.</p>
     * <p>Model object under {@link #USER} key - user under {@link #USERID} from DB.</p>
     * <p>Model object under {@link #MAP} key - Each key presents a project, value presents an ArrayList of task
     * within the project that are assigned to current user. Map doesn't contain projects where tasks assigned to current user doesn't exist</p>
     * <p>Model object under {@link #TASK_DETAIL_RESOURCE} key with resource value {@link #taskDetail}-
     * For user to be able to navigate to task detail page from user detail page.</p>
     * <p>Model object under {@link #BUTTON_LABEL} key represents label of submit button on user detail page.
     * <p>Model object under {@link #BUTTON_REDIRECTION_PAGE} logs out.
     * <p>Model object under {@link #BACK} key - a hint URL path for the page {@link #TASK_DETAIL_RESOURCE}
     * to be redirected.</p>
     * <p>Model object under {@link #HOME} key - base URL path for "common" module</p>
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName(); //get logged in username

        UserDTO user = userManager.getUserByUsername(authName);
        HashMap<String, ArrayList<TaskDTO>>  map = taskManager.userTasksMap(user.getId().toString());

        modelAndView.addObject(USER, user);
        modelAndView.addObject(MAP, map);
        modelAndView.addObject(TASK_DETAIL_RESOURCE, "task");
        modelAndView.addObject(BUTTON_LABEL, "LOGOUT");
        modelAndView.addObject(BUTTON_REDIRECTION_PAGE, "logout");

        modelAndView.addObject(BACK, "");
        modelAndView.addObject(HOME, "common");

        modelAndView.setViewName("user_detail");
        return modelAndView;
    }

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public ModelAndView createTask(HttpServletRequest request){
        String received_redirect_modify_task_to = request.getParameter(BACK);
        String receivedError = request.getParameter(ERROR);

        Collection<UserDTO> users = userManager.allUsersByRole(environment.getProperty("role_user"));
        Collection<ProjectDTO> projects = projectManager.allProjects();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ERROR, receivedError);
        modelAndView.addObject(USERS, users);
        modelAndView.addObject(PROJECTS, projects);
        modelAndView.setViewName(CREATE_TASK_JSP);
        modelAndView.addObject(REDIRECT_MODIFY_TO, received_redirect_modify_task_to);
        modelAndView.addObject(MODIFY, TASK_RESOURCES_PATH);
        modelAndView.addObject(HOME, HOME_VALUE);

        return modelAndView;
    }

    /**
     * Provides a JSP form view to display existing specified task detail.
     * Is invoked by clicking on the name of task from tasks list rom {@link #userHome()} URL.
     * @param taskId Id of task under {@link #TASKID} on which Story a click has occur.
     * @param back- a hint URL path under {@link #BACK} key , for {@link #modifyTask} to be redirected,
     *                                key keep the path of page fom where current {@link #taskDetail} was called.
     * @return
     * <p>ModelAndView object with JSP view named "task_detail" under {@link #TASK_DETAIL_JSP} key.</p>
     * <p>Model object under {@link #USERS} key - all users list with role type of "USER" under {@link #USERS} key  from DB.</p>
     * <p>Model object under {@link #TASK} key - The task object from DB</p>
     * <p>Model object under {@link #HOME} key - base URL path for "admin" module</p>
     * <p>Model object under {@link #MODIFY} key - universal mid- resource which is called when ""Submit" is pressed.
     * Eee {@link #modifyTask} resource</p>
     * <p>Model object under {@link #BACK} key - URL path for the page from where {@link #taskDetail} resource was called.</p>
     * <p>Model object under {@link #ERROR} key - error message in case if a redirection occure to current page in due to passed empty input values.</p>
     */
    @RequestMapping(value = "/task/{taskId}", method = RequestMethod.GET)
    public ModelAndView taskDetail(HttpServletRequest request, @PathVariable("taskId") String taskId, @RequestParam(value="back", required=false) String back){
        String receivedError = request.getParameter(ERROR);

        TaskDTO task = taskManager.getTaskById(taskId);
        Collection<UserDTO> users = userManager.allUsersByRole(environment.getProperty("role_user"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(USERS, users);
        modelAndView.addObject(TASK, task);
        modelAndView.addObject(HOME, HOME_VALUE);
        modelAndView.addObject(MODIFY, TASK_RESOURCES_PATH);
        modelAndView.addObject(BACK, back);
        modelAndView.addObject(ERROR, receivedError);
        modelAndView.setViewName(TASK_DETAIL_JSP);
        return modelAndView;
    }

    /**
     *  <p>"Submit" in a {@link #createTask}  or {@link #taskDetail} resources invokes
     *  a call to a mid resource {@link #modifyTask} under {@link #MODIFY} key.</p>
     *  <p>{@link #modifyTask} doesn't hold view, it is only a mid resource to invoke task creation or modification
     *  business logic holder method and redirect context to resource page under {@link #BACK} key</p>
     *  <p> request - is used to take parameters fro request body. Where
     *  <p> {@link #TASK_STORY} Story from {@link #createTask} filled form or modified-yes/no value from {@link #taskDetail} form, NotNull field.</p>
     *  <p> {@link #TASK_DESCRIPTION} description from {@link #createTask} filled form or modified-yes/no value from {@link #taskDetail} form, NotNull field.
     *  <p> {@link #TASKID} of task under {@link #TASKID} on which Story a click has occur.
     *  <p> {@link #PROJECTID} of project under {@link #PROJECTID}
     *  <p> {@link #USERID} Id of user , new choosed asignee</p>
     *  <p> {@link #USER_ID_FOR_USERTD} Id of user, is assignee</p>
     *  <p> {@link #BACK} key - URL path for the page from where {@link #createTask} or {@link #taskDetail} resource was called</p>
     *  @return
     *  <p>ModelAndView object with JSP view under {@link #BACK} key.</p>
     */
    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public String modifyTask(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String task_story = request.getParameter(TASK_STORY);
        String task_description = request.getParameter(TASK_DESCRIPTION);
        String taskid = request.getParameter(TASKID);
        String projectid = request.getParameter(PROJECTID);
        String userid = request.getParameter(USERID);
        String userIdForUserDT = request.getParameter(USER_ID_FOR_USERTD);
        String back = request.getParameter(BACK);

        String currentAssigneeId;
        if(userid == null || userid.isEmpty())currentAssigneeId = userIdForUserDT;
        else currentAssigneeId = userid;

        // Check if any input field is empty redirect to source page with error message.
        if(task_story.isEmpty() || task_description.isEmpty() || projectid == null || projectid.isEmpty()
                || currentAssigneeId == null || currentAssigneeId.isEmpty()){
            redirectAttributes.addAttribute("error", "Please fill all fields!");
            return "redirect:" + request.getHeader("Referer");
        }

        // Success redirect
        taskManager.addOrModifyTask(taskid, task_story, task_description, projectid, userid);
        if(back == null || back.isEmpty()) return "redirect:/" + HOME_VALUE;
        return "redirect:/" + HOME_VALUE + back;
    }
}
