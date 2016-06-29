package com.springmvc.demo.controller;

import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.TaskManager;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
 * <p>{@link #taskDetail(String, String, String)}</p>
 * <p>{@link #modifyTask(String, String, String, String, String, String, String)}</p>
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    // region Static Strings
    // Keys for passing resource string names
    public static final String TASK_DETAIL_RESOURCE = "task_detail_resource";
    public static final String HOME = "home";
    public static final String REDIRECT_MODIFY_TO = "redirect_modify_to";
    public static final String REDIRECT_MODIFY_TASK_TO = "redirect_modify_task_to";
    public static final String BUTTON_REDIRECTION_PAGE = "button_redirection_page";
    public static final String MODIFY = "modify";

    // JSP's names
    public static final String USER_DETAIL = "user_detail";
    public static final String TASK_DETAIL = "task_detail";

    // Keys for passing objects
    public static final String USERS = "users";

    public static final String USER = "user";
    public static final String USERID = "userId";

    public static final String PROJECTID = "projectId";

    public static final String TASK = "task";
    public static final String TASKID = "taskId";
    public static final String TASK_STORY = "task_story";
    public static final String TASK_DESCRIPTION = "task_description";

    public static final String MAP = "map";

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

    // region User
    /**
     * Provides a JSP form view to display existing specified user detail.
     * Is invoked by clicking on the name of user from users list in {@link #userHome()} URL.
     * @return
     * <p>ModelAndView object with JSP view named "user_detail" under {@link #USER_DETAIL} key.</p>
     * <p>Model object under {@link #USER} key - user under {@link #USERID} from DB.</p>
     * <p>Model object under {@link #MAP} key - Each key presents a project, value presents an ArrayList of task
     * within the project that are assigned to current user. Map doesn't contain projects where tasks assigned to current user doesn't exist</p>
     * <p>Model object under {@link #TASK_DETAIL_RESOURCE} key with resource value {@link #taskDetail}-
     * For user to be able to navigate to task detail page from user detail page.</p>
     * <p>Model object under {@link #BUTTON_LABEL} key represents label of submit button on user detail page.
     * <p>Model object under {@link #BUTTON_REDIRECTION_PAGE} logs out.
     * <p>Model object under {@link #REDIRECT_MODIFY_TASK_TO} key - a hint URL path for the page {@link #TASK_DETAIL_RESOURCE}
     * to be redirected.</p>
     * <p>Model object under {@link #HOME} key - base URL path for "common" module</p>
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView userHome(){
        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName(); //get logged in username

        UserDTO user = userManager.getUserByUsername(authName);
        HashMap<String, ArrayList<TaskDTO>>  map = taskManager.userTasks(user);

        modelAndView.addObject(USER, user);
        modelAndView.addObject(MAP, map);
        modelAndView.addObject(TASK_DETAIL_RESOURCE, "task_detail");
        modelAndView.addObject(BUTTON_LABEL, "LOGOUT");
        modelAndView.addObject(BUTTON_REDIRECTION_PAGE, "/logout");

        modelAndView.addObject(REDIRECT_MODIFY_TASK_TO, "");
        modelAndView.addObject(HOME, "common");

        modelAndView.setViewName(USER_DETAIL);
        return modelAndView;
    }
    // endregion

    // region Task
    /**
     * Provides a JSP form view to display existing specified task detail.
     * Is invoked by clicking on the name of task from tasks list {@link #userHome()} URL.
     * @param taskId Id of task under {@link #TASKID} on which Story a click has occur.
     * @param redirect_modify_task_to - a hint URL path under {@link #REDIRECT_MODIFY_TASK_TO} key , for {@link #modifyTask} to be redirected,
     *                                key keep the path of page from where current {@link #taskDetail} was called.
     * @param home Home redirection URL.
     * @return
     * <p>ModelAndView object with JSP view named "task_detail" under {@link #TASK_DETAIL} key.</p>
     * <p>Model object under {@link #USERS} key - all users list with role type of "USER" under {@link #USERS} key  from DB.</p>
     * <p>Model object under {@link #TASK} key - The task object from DB</p>
     * <p>Model object under {@link #HOME} key - base URL path for "admin" module</p>
     * <p>Model object under {@link #MODIFY} key - universal mid- resource which is called when ""Submit" is pressed.
     * Eee {@link #modifyTask} resource</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where {@link #taskDetail} resource was called</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TASK_TO} key - a hint URL path for the page {@link #TASK_DETAIL_RESOURCE}
     * to be redirected.</p>
     */
    @RequestMapping(value = "/task_detail", method = RequestMethod.GET)
    public ModelAndView taskDetail(@ModelAttribute(TASKID) String taskId,
                                   @ModelAttribute(REDIRECT_MODIFY_TASK_TO) String redirect_modify_task_to,
                                   @ModelAttribute(HOME) String home){
        if (home.isEmpty()) throw new EmptyRequiredValueException();
        ModelAndView modelAndView = new ModelAndView();

        TaskDTO task = taskManager.getTaskById(taskId);
        Collection<UserDTO> users = userManager.allUsersByRole(environment.getProperty("role_user"));

        modelAndView.addObject(USERS, users);
        modelAndView.addObject(TASK, task);
        modelAndView.addObject(HOME, home);
        modelAndView.addObject(MODIFY, "modify_task");
        modelAndView.addObject(REDIRECT_MODIFY_TO, redirect_modify_task_to);
        modelAndView.setViewName(TASK_DETAIL);
        return modelAndView;
    }

    /**
     *  <p>"Submit" in a {@link #taskDetail} resources invokes
     *  a call to a mid resource {@link #modifyTask} under {@link #MODIFY} key.</p>
     *  <p>{@link #modifyTask} doesn't hold view, it is only a mid resource to invoke modification
     *  business logic holder method and redirect context to resource page under {@link #REDIRECT_MODIFY_TO} key</p>
     *  @param taskStory Story from modified-yes/no value from {@link #taskDetail} form, NotNull field.
     *  @param taskDescription description from  modified-yes/no value from {@link #taskDetail} form, NotNull field.
     *  @param taskId Id of task under {@link #TASKID} on which Story a click has occur, @NotNull field.
     *  @param projectId Id of project under {@link #PROJECTID}, @NotNull field.
     *  @param userId Id of user under {@link #USERID}, @NotNull field.
     *  @param redirect_modify_to under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where  {@link #taskDetail} resource was called</p>
     *  @param home Home redirection URL.
     *  @return
     *  <p>ModelAndView object with JSP view under {@link #REDIRECT_MODIFY_TO} key.</p>
     */
    @RequestMapping(value = "/modify_task", method = RequestMethod.POST)
    public ModelAndView modifyTask(@ModelAttribute(TASK_STORY) String taskStory,
                                   @ModelAttribute(TASK_DESCRIPTION) String taskDescription,
                                   @ModelAttribute(TASKID) String taskId,
                                   @ModelAttribute(PROJECTID) String projectId,
                                   @ModelAttribute(USERID) String userId,
                                   @ModelAttribute(REDIRECT_MODIFY_TO) String redirect_modify_to,
                                   @ModelAttribute(HOME) String home){
        if (home.isEmpty()) throw new EmptyRequiredValueException();
        TaskDTO task = taskManager.addOrModifyTask(taskId, taskStory, taskDescription, projectId, userId);
        String taskProjectId = task.getProjectDTO().getId().toString();
        String taskUserId = task.getUserDTO().getId().toString();

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/" + home + redirect_modify_to + "?success=true&projectId=" + taskProjectId + "&userId=" + taskUserId + "&home=" + home);
        return modelAndView;
    }
    // endregion
}
