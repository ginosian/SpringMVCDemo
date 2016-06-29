package com.springmvc.demo.controller;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import com.springmvc.demo.exceptions.RuntimeErrorMessage;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.TaskManager;
import com.springmvc.demo.services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Martha on 6/20/2016.
 */

/**
 * Only users with role ADMIN specified in the config.properties file have access to this
 * resource. This controller provides all the administrator functionality.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    // region Static Strings
    // Keys for passing resource string names
    public static final String USER_DETAIL_RESOURCE = "user_detail_resource";
    public static final String CREATE_USER_RESOURCE = "create_user_resource";
    public static final String PROJECT_DETAIL_RESOURCE = "project_detail_resource";
    public static final String CREATE_PROJECT_RESOURCE = "create_project_resource";
    public static final String TASK_DETAIL_RESOURCE = "task_detail_resource";
    public static final String CREATE_TASK_RESOURCE = "create_task_resource";
    public static final String HOME = "home";
    public static final String REDIRECT_MODIFY_TO = "redirect_modify_to";
    public static final String REDIRECT_MODIFY_TASK_TO = "redirect_modify_task_to";
    public static final String MODIFY = "modify";

    // JSP's names
    public static final String ADMIN_PAGE = "admin_page";
    public static final String CREATE_USER = "create_user";
    public static final String USER_DETAIL = "user_detail";
    public static final String CREATE_PROJECT = "create_project";
    public static final String PROJECT_DETAIL = "project_detail";
    public static final String CREATE_TASK = "create_task";
    public static final String TASK_DETAIL = "task_detail";
    public static final String CREATE_TASK_FROM_PROJECT = "create_task_from_project";

    // Keys for passing objects
    public static final String USERS = "users";
    public static final String PROJECTS = "projects";
    public static final String TASKS = "tasks";

    public static final String ROLES = "roles";
    public static final String USER = "user";
    public static final String USERID = "userId";
    public static final String USERNAME = "username";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";

    public static final String PROJECT = "project";
    public static final String PROJECTID = "projectId";
    public static final String PROJECT_STORY = "project_story";
    public static final String PROJECT_DESCRIPTION = "project_description";
    public static final String PROJECT_TASKS = "project_tasks";

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


    // region Home
    /**
     * Provides all tasks, projects and also the user list of the application.
     * Only users with role USER specified in config.properties file are included in the returned
     * user list.
     * Also provides URL-s for creation and modification of above mentioned data objects.
     * REDIRECT_MODIFY_TASK_TO parameter is returned to hint task detail page where to be redirected,
     * as task detail page can be accessed from other pages.
     * @return Admin home view with appropriate model.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView();

        // Add user list and related resource paths.
        modelAndView.addObject(USERS, userManager.allUsersByRole(new RoleDTO(environment.getProperty("role_user")))); // Users list with "USER" role
        modelAndView.addObject(USER_DETAIL_RESOURCE, USER_DETAIL); // A resource to link to user detail page
        modelAndView.addObject(CREATE_USER_RESOURCE, CREATE_USER); // A resource to link to create user page

        // Add project list and related resource paths.
        modelAndView.addObject(PROJECTS, projectManager.allProjects());  // All projects list.
        modelAndView.addObject(PROJECT_DETAIL_RESOURCE, PROJECT_DETAIL); // A resource to link to project detail page
        modelAndView.addObject(CREATE_PROJECT_RESOURCE, CREATE_PROJECT); // A resource to link to create project page

        // Add task list and related resource paths.
        modelAndView.addObject(TASKS, taskManager.allTasks(true)); // All task list.
        modelAndView.addObject(TASK_DETAIL_RESOURCE, TASK_DETAIL); // A resource to link to task detail page
        modelAndView.addObject(CREATE_TASK_RESOURCE, CREATE_TASK); // A resource to link to create task page
        modelAndView.addObject(REDIRECT_MODIFY_TASK_TO, "");

        // Set home resource name.
        modelAndView.addObject(HOME, "admin"); // Sub-root URL
        // Set current view name.
        modelAndView.setViewName(ADMIN_PAGE); // JSP name

        return modelAndView;
    }
    // endregion

    // region User

    /**
     * Provides a JSP form view for user creation.
     * @param home Home redirection URL.
     * @return
     * <p>ModelAndView object with JSP view named "create_user" under {@link #CREATE_USER} key.</p>
     * <p>Model object under {@link #ROLES} key - List of existing role types in DB.</p>
     * <p>Model object under {@link #HOME} key - base URL path for "admin" module</p>
     * <p>Model object under {@link #MODIFY} key - universal mid- resource which is called when ""Submit" is pressed.
     * see {@link #register}</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where {@link #createUser} resource was called</p>
     */
    @RequestMapping(value = "/create_user", method = RequestMethod.GET)
    public ModelAndView createUser(@ModelAttribute(HOME) String home){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(ROLES, userManager.allRoles());
        modelAndView.addObject(HOME, home);
        modelAndView.addObject(MODIFY, "register");
        modelAndView.addObject(REDIRECT_MODIFY_TO, "");
        modelAndView.setViewName(CREATE_USER);
        return modelAndView;
    }

    /**
     *  <p>"Submit" in {@link #createUser}  resource invokes
     *  a call to a mid resource {@link #register} under {@link #MODIFY} key.</p>
     *  <p>{@link #register} doesn't hold view, it is only a mid resource to invoke user creation business logic holder method
     *  and redirect context to resource page under {@link #REDIRECT_MODIFY_TO} key</p>
     *  @param username username from {@link #createUser} filled form, NotNull and mail type field. Provided by user.
     *  @param name name of user to be displayed, NotNull field.  Provided by user.
     *  @param password can have nay length can hold eny character.  Provided by user.
     *  @param role to be choose from drop down list, NotNull field.  Provided by user.
     *  @param home Home redirection URL.
     *  @return If username exist in DB a redirection will be made back to {@link #createUser} resource else back to {@link #adminHome()} homepage.
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ExceptionHandler(RuntimeErrorMessage.class)
    public ModelAndView register(@ModelAttribute(USERNAME)String username,
                                 @ModelAttribute(NAME) String name,
                                 @ModelAttribute(PASSWORD) String password,
                                 @ModelAttribute(ROLE) String role,
                                 @ModelAttribute(HOME) String home){
        ModelAndView modelAndView = new ModelAndView();
        if(userManager.getUserByUsername(username) != null){
            modelAndView.setViewName("redirect:/" + home + "/create_user");
            return modelAndView;
        }
        userManager.addUser(name, username, password, true, role);
        modelAndView.setViewName("redirect:/" + home + "?success=true");
        return modelAndView;
    }
    // endregion

    // region Project

    // region Done
    /**
     * Provides a JSP form view to display existing specified project detail.
     * Is invoked by clicking on the name of project from project list in {@link #adminHome} URL.
     * @param projectId Id of project under {@link #PROJECTID} on which Story a click has occur.
     * @param home Home redirection URL.
     * @return
     * <p>ModelAndView object with JSP view named "project_detail" under {@link #PROJECT_DETAIL} key.</p>
     * <p>Model object under {@link #PROJECT} key - project under {@link #PROJECTID} from DB.</p>
     * <p>Model object under {@link #PROJECT_TASKS} key - Tasks list assigned to this project</p>
     * <p>Model object under {@link #TASK_DETAIL_RESOURCE} key with resource value {@link #taskDetail}-
     * For user to be able to navigate to task detail page from project detail page.</p>
     * <p>Model object under {@link #CREATE_TASK_RESOURCE} key with resource value {@link #createTask}-
     * For user to be able to navigate to create task page and assign new task to this project</p>
     * <p>Model object under {@link #HOME} key - base URL path for "admin" module</p>
     * <p>Model object under {@link #MODIFY} key - universal mid- resource which is called when ""Submit" is pressed.
     * see {@link #modifyProject}</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where {@link #projectDetail} resource was called</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TASK_TO} key - a hint URL path for the pages {@link #TASK_DETAIL_RESOURCE}
     * and {@link #CREATE_TASK_RESOURCE} to be redirected. For example if task detail was navigated from project detail page, task detail page
     * submit should navigate back to project detail page.</p>
     */
    @RequestMapping(value = "/project_detail", method = RequestMethod.GET)
    public ModelAndView projectDetail(@ModelAttribute(PROJECTID) String projectId,
                                      @ModelAttribute(HOME) String home){
        ModelAndView modelAndView = new ModelAndView();

        ProjectDTO project = projectManager.getProjectById(projectId);
        Collection<TaskDTO> tasks = taskManager.getTasksWithinProject(projectId);

        modelAndView.addObject(PROJECT, project);
        modelAndView.addObject(PROJECT_TASKS, tasks);

        modelAndView.addObject(TASK_DETAIL_RESOURCE, TASK_DETAIL);
        modelAndView.addObject(CREATE_TASK_RESOURCE, CREATE_TASK);

        modelAndView.addObject(HOME, home);
        modelAndView.addObject(MODIFY, "modify_project");
        modelAndView.addObject(REDIRECT_MODIFY_TO, "");
        modelAndView.addObject(REDIRECT_MODIFY_TASK_TO, "/project_detail");
        modelAndView.setViewName(PROJECT_DETAIL);
        return modelAndView;
    }

    /**
     * Provides a JSP form view for project creation.
     * @param home Home redirection URL.
     * @return
     * <p>ModelAndView object with JSP view named "create_project" under {@link #CREATE_PROJECT} key.</p>
     * <p>Model object under {@link #HOME} key - base URL path for "admin" module</p>
     * <p>Model object under {@link #MODIFY} key - universal mid- resource which is called when ""Submit" is pressed.
     * see {@link #modifyProject}</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where {@link #createProject} resource was called</p>
     */
    @RequestMapping(value = "/create_project", method = RequestMethod.GET)
    public ModelAndView createProject(@ModelAttribute(HOME) String home){

        if(home == null || home.isEmpty()) throw new EmptyRequiredValueException();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(HOME, home);
        modelAndView.addObject(MODIFY, "modify_project");
        modelAndView.addObject(REDIRECT_MODIFY_TO, "");

        modelAndView.setViewName(CREATE_PROJECT);

        return modelAndView;
    }


    /**
     *  <p>"Submit" in a {@link #createProject}  or {@link #projectDetail} resources invokes
     *  a call to a mid resource {@link #modifyProject} under {@link #MODIFY} key.</p>
     *  <p>{@link #modifyProject} doesn't hold view, it is only a mid resource to invoke project creation or modification
     *  business logic holder method and redirect context to resource page under {@link #REDIRECT_MODIFY_TO} key</p>
     *  @param projectStory Story from {@link #createProject} filled form or modified value from {@link #projectDetail} form, NotNull field.
     *  @param project_description description from {@link #createProject} filled form or modified value from {@link #projectDetail} form, NotNull field.
     *  @param id Id of project under {@link #PROJECTID} on which Story a click has occur
     *  @param home Home redirection URL.
     *  @param redirect_modify_to under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where {@link #createProject} or {@link #projectDetail} resource was called</p>
     *  @return
     *  <p>ModelAndView object with JSP view under {@link #REDIRECT_MODIFY_TO} key.</p>
     */
    @RequestMapping(value = "/modify_project", method = RequestMethod.POST)
    public ModelAndView modifyProject(@ModelAttribute(PROJECT_STORY) String projectStory,
                                      @ModelAttribute(PROJECT_DESCRIPTION) String project_description,
                                      @ModelAttribute(PROJECTID) String id,
                                      @ModelAttribute(HOME) String home,
                                      @ModelAttribute(REDIRECT_MODIFY_TO) String redirect_modify_to){
        ModelAndView modelAndView = new ModelAndView();
        projectManager.addOrUpdateProject(id, projectStory, project_description);
        modelAndView.setViewName("redirect:/" + home + redirect_modify_to + "?success=true");
        return modelAndView;
    }

    /**
     * Provides a JSP form view to display existing specified task detail.
     * Is invoked by clicking on the name of task from tasks list either from {@link #adminHome} or from {@link #projectDetail}
     * or from {@link #userDetail} URLs.
     * @param taskId Id of task under {@link #TASKID} on which Story a click has occur.
     * @param home Home redirection URL.
     * @return
     * <p>ModelAndView object with JSP view named "project_detail" under {@link #PROJECT_DETAIL} key.</p>
     * <p>Model object under {@link #PROJECT} key - project under {@link #PROJECTID} from DB.</p>
     * <p>Model object under {@link #PROJECT_TASKS} key - Tasks list assigned to this project</p>
     * <p>Model object under {@link #TASK_DETAIL_RESOURCE} key with resource value {@link #taskDetail}-
     * For user to be able to navigate to task detail page from project detail page.</p>
     * <p>Model object under {@link #CREATE_TASK_RESOURCE} key with resource value {@link #createTask}-
     * For user to be able to navigate to create task page and assign new task to this project</p>
     * <p>Model object under {@link #HOME} key - base URL path for "admin" module</p>
     * <p>Model object under {@link #MODIFY} key - universal mid- resource which is called when ""Submit" is pressed.
     * see {@link #modifyProject}</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TO} key - URL path for the page from where {@link #projectDetail} resource was called</p>
     * <p>Model object under {@link #REDIRECT_MODIFY_TASK_TO} key - a hint URL path for the pages {@link #TASK_DETAIL_RESOURCE}
     * and {@link #CREATE_TASK_RESOURCE} to be redirected. For example if task detail was navigated from project detail page, task detail page
     * submit should navigate back to project detail page.</p>
     */
    @RequestMapping(value = "/task_detail", method = RequestMethod.GET)
    public ModelAndView taskDetail(@ModelAttribute(TASKID) String taskId,
                                   @ModelAttribute(HOME) String home,
                                   @ModelAttribute(REDIRECT_MODIFY_TASK_TO) String redirect_modify_task_to){

        ModelAndView modelAndView = new ModelAndView();

        TaskDTO task = taskManager.getTaskById(taskId);
        Collection<UserDTO> users = userManager.allUsersByRole(environment.getProperty("role_user"));

        modelAndView.addObject(USERS, users);
        modelAndView.addObject(TASK, task);

        if(redirect_modify_task_to == null || redirect_modify_task_to.isEmpty()){
            modelAndView.addObject(REDIRECT_MODIFY_TO, "");
        } else {modelAndView.addObject(REDIRECT_MODIFY_TO, redirect_modify_task_to);}

        modelAndView.addObject(HOME, home);
        modelAndView.addObject(MODIFY, "modify_task");
        modelAndView.setViewName(TASK_DETAIL);
        return modelAndView;
    }

    @RequestMapping(value = "/create_task", method = RequestMethod.GET)
    public ModelAndView createTask(@ModelAttribute(PROJECTID) String projectId,
                                   @ModelAttribute(HOME) String home,
                                   @ModelAttribute(REDIRECT_MODIFY_TASK_TO) String redirect_modify_task_to){

        if (home.isEmpty()) throw new EmptyRequiredValueException();

        ModelAndView modelAndView = new ModelAndView();

        Collection<UserDTO> users = userManager.allUsersByRole(environment.getProperty("role_user"));
        modelAndView.addObject(USERS, users);

        if(projectId == null || projectId.isEmpty()){
            Collection<ProjectDTO> projects = projectManager.allProjects();
            modelAndView.addObject(PROJECTS, projects);
            modelAndView.setViewName(CREATE_TASK);
        } else {
            ProjectDTO project = projectManager.getProjectById(projectId);
            modelAndView.addObject(PROJECT, project);
            modelAndView.setViewName(CREATE_TASK_FROM_PROJECT);
        }

        if(redirect_modify_task_to == null || redirect_modify_task_to.isEmpty()){
            modelAndView.addObject(REDIRECT_MODIFY_TO, "");
        } else {modelAndView.addObject(REDIRECT_MODIFY_TO, redirect_modify_task_to);}
        modelAndView.addObject(HOME, home);
        modelAndView.addObject(MODIFY, "modify_task");

        return modelAndView;
    }

    @RequestMapping(value = "/modify_task", method = RequestMethod.POST)
    public ModelAndView modifyTask(@ModelAttribute(TASK_STORY) String taskStory,                    //yes   //yes   //yes
                                   @ModelAttribute(TASK_DESCRIPTION) String taskDescription,        //yes   //yes   //yes
                                   @ModelAttribute(TASKID) String taskId,                           //no    //yes   //yes
                                   @ModelAttribute(PROJECTID) String projectId,                     //yes   //yes   //yes
                                   @ModelAttribute(USERID) String newAssignee,                      //yes   //no    //no
                                   @ModelAttribute(REDIRECT_MODIFY_TO) String redirect_modify_to,   //no    //no    //no
                                   @ModelAttribute(HOME) String home){                              //yes   //yes   //yes

        UserDTO assignee;
        if(taskId == null || taskId.isEmpty()){
            TaskDTO newTask = new TaskDTO();
            ProjectDTO tasksProject = projectManager.getProjectById(projectId);
            assignee = userManager.getUserById(newAssignee);
            newTask.set(taskStory, taskDescription, tasksProject, assignee);
            taskManager.addTask(newTask);
        } else{
            TaskDTO taskDTO = taskManager.getTaskById(taskId);
            if(newAssignee == null || newAssignee.isEmpty()){
                assignee = userManager.getUserByName(taskDTO.getUserDTO().getName());
            } else {assignee = userManager.getUserById(newAssignee);}
            taskDTO.set(taskStory, taskDescription, assignee);
            taskManager.modifyTask(taskDTO);
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:/" + home + redirect_modify_to + "?success=true&projectId=" + projectId + "&userId=" + assignee.getId() + "&home=" + home);
        return modelAndView;
    }

    // endregion

    // endregion

    // region User
    @RequestMapping(value = "/user_detail", method = RequestMethod.GET)
    public ModelAndView userDetail(@ModelAttribute(USERID) String userId,
                                   @ModelAttribute(HOME) String home){
        ModelAndView modelAndView = new ModelAndView();

        UserDTO user = userManager.getUserById(userId);
        HashMap<String, ArrayList<TaskDTO>>  map = taskManager.userTasks(user);

        modelAndView.addObject(USER, user);
        modelAndView.addObject(MAP, map);
        modelAndView.addObject(TASK_DETAIL_RESOURCE, "task_detail");
        modelAndView.addObject(BUTTON_LABEL, "SUBMIT");

        modelAndView.addObject(REDIRECT_MODIFY_TASK_TO, "/user_detail");
        modelAndView.addObject(HOME, home);

        modelAndView.setViewName(USER_DETAIL);
        return modelAndView;
    }


    // endregion


}
