package com.springmvc.demo.controller;

import com.springmvc.demo.dto.ProjectDTO;
import com.springmvc.demo.dto.RoleDTO;
import com.springmvc.demo.dto.TaskDTO;
import com.springmvc.demo.dto.UserDTO;
import com.springmvc.demo.exceptions.EmptyRequiredValueException;
import com.springmvc.demo.services.ProjectManager;
import com.springmvc.demo.services.TaskManager;
import com.springmvc.demo.services.UserManager;
import com.springmvc.demo.utils.DTOMockUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by Martha on 6/27/2016.
 */
public class AdminControllerTest {
    MockMvc mockMvc;

    @Mock
    UserManager userManager;
    @Mock
    TaskManager taskManager;
    @Mock
    ProjectManager projectManager;
    @Mock
    MockHttpServletRequest request;
    @Mock
    Environment environment;

    @InjectMocks
    AdminController adminController;

    InternalResourceViewResolver viewResolver;

    private static String REFERER;
    private static String VALID_USERNAME;
    private static int USER_QUANTITY;
    private static int PROJECT_QUANTITY;
    private static int TASK_QUANTITY;
    private static int ROLE_QUANTITY;

    @Before
    public void setup(){
        VALID_USERNAME = "valid@test.com";
        USER_QUANTITY = 5;
        PROJECT_QUANTITY = 3;
        TASK_QUANTITY = 12;
        ROLE_QUANTITY = 2;

        MockitoAnnotations.initMocks(this);
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/");
        viewResolver.setSuffix(".jsp");
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).setViewResolvers(viewResolver).build();

        when(environment.getProperty(anyString())).thenAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                String arg = (String)invocation.getArguments()[0];
                if (arg.equals("role_user")) return "USER";
                if (arg.equals("role_admin")) return "ADMIN";
                return null;
            }
        });
        when(userManager.allRoles()).thenAnswer(new Answer<ArrayList<RoleDTO>>() {
            @Override
            public ArrayList<RoleDTO> answer(InvocationOnMock invocation) throws Throwable {
                return DTOMockUtils.generateRoleList(ROLE_QUANTITY);
            }
        });
        when(userManager.allUsersByRole(anyString())).thenAnswer(new Answer<ArrayList<UserDTO>>() {
            @Override
            public ArrayList<UserDTO> answer(InvocationOnMock invocation) throws Throwable {
                if (invocation.getArguments()[0] == null) throw new EmptyRequiredValueException();
                String role = (String) invocation.getArguments()[0];
                if (role.isEmpty()) throw new EmptyRequiredValueException();
                ArrayList<UserDTO> userList = DTOMockUtils.generateUsersList(USER_QUANTITY);
                for (UserDTO user : userList) {
                    Set<RoleDTO> roles = new HashSet<RoleDTO>();
                    roles.add(new RoleDTO(role));
                    user.setUserRoles(roles);
                }
                return userList;
            }
        });
        when(userManager.getUserByUsername(anyString())).thenAnswer(new Answer<UserDTO>() {
            @Override
            public UserDTO answer(InvocationOnMock invocation) throws Throwable {
                String argument = (String) invocation.getArguments()[0];
                if (argument.equals(VALID_USERNAME)) {
                    UserDTO userDTO = DTOMockUtils.generateUser();
                    userDTO.setUsername(VALID_USERNAME);
                    return userDTO;
                } else if (argument.isEmpty()) {
                    throw new EmptyRequiredValueException();
                } else {
                    return null;
                }
            }
        });
        when(userManager.addUser(anyString(), anyString(), anyString(), anyBoolean(), anyString())).thenAnswer(new Answer<UserDTO>() {
            @Override
            public UserDTO answer(InvocationOnMock invocation) throws Throwable {
                String name = (String) invocation.getArguments()[0];
                String userName = (String) invocation.getArguments()[1];
                String password = (String) invocation.getArguments()[2];
                boolean enabled = (boolean) invocation.getArguments()[3];
                String role = (String) invocation.getArguments()[4];

                if(userName == null || userName.isEmpty()
                        || password == null || password.isEmpty()
                        || name == null || name.isEmpty()
                        || role == null || role.isEmpty()) throw new EmptyRequiredValueException();
                UserDTO user = DTOMockUtils.generateUser();
                Set<RoleDTO> roles = new HashSet<>();
                roles.add(new RoleDTO(role));

                user.set(userName, password, name, enabled, roles);
                return user;
            }
        });
        when(userManager.getUserById(anyString())).thenAnswer(new Answer<UserDTO>() {
            @Override
            public UserDTO answer(InvocationOnMock invocation) throws Throwable {
                String id = (String)invocation.getArguments()[0];
                if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
                UserDTO user = DTOMockUtils.generateUser();
                user.setId(Long.parseLong(id));
                return user;
            }
        });


        when(projectManager.allProjects()).thenAnswer(new Answer<ArrayList<ProjectDTO>>() {
            @Override
            public ArrayList<ProjectDTO> answer(InvocationOnMock invocation) throws Throwable {
                return DTOMockUtils.generateProjectList(PROJECT_QUANTITY);
            }
        });
        when(projectManager.getProjectById(anyString())).thenAnswer(new Answer<ProjectDTO>() {
            @Override
            public ProjectDTO answer(InvocationOnMock invocation) throws Throwable {
                String id = (String)invocation.getArguments()[0];
                if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
                ProjectDTO projectDTO = DTOMockUtils.generateProject();
                projectDTO.setId(Long.parseLong(id));
                return projectDTO;
            }
        });
        when(projectManager.addOrUpdateProject(anyString(), anyString(), anyString())).thenAnswer(new Answer<ProjectDTO>() {
            @Override
            public ProjectDTO answer(InvocationOnMock invocation) throws Throwable {
                ProjectDTO project = DTOMockUtils.generateProject();
                String story = (String)invocation.getArguments()[1];
                String id = (String)invocation.getArguments()[0];
                if(story == null || story.isEmpty() || id == null || id.isEmpty()) throw new EmptyRequiredValueException();
                project.set(story, (String)invocation.getArguments()[2]);
                project.setId(Long.parseLong(id));
                return project;
            }
        });



        when(taskManager.allTasks()).thenAnswer(new Answer<ArrayList<TaskDTO>>() {
            @Override
            public ArrayList<TaskDTO> answer(InvocationOnMock invocation) throws Throwable {
                return DTOMockUtils.generateTasksList(TASK_QUANTITY);
            }
        });
        when(taskManager.getTasksWithinProject(anyString())).thenAnswer(new Answer<ArrayList<TaskDTO>>() {
            @Override
            public ArrayList<TaskDTO> answer(InvocationOnMock invocation) throws Throwable {
                String id = (String) invocation.getArguments()[0];
                if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
                ProjectDTO project = DTOMockUtils.generateProject();
                ArrayList<TaskDTO> taskList = DTOMockUtils.generateTasksList(TASK_QUANTITY);
                for (int i = 0; i < TASK_QUANTITY; i++) {
                    taskList.get(i).setProjectDTO(project);
                }
                return taskList;
            }
        });
        when(taskManager.getTaskById(anyString())).thenAnswer(new Answer<TaskDTO>() {
            @Override
            public TaskDTO answer(InvocationOnMock invocation) throws Throwable {
                String id = (String)invocation.getArguments()[0];
                if (id == null || id.isEmpty()) throw new EmptyRequiredValueException();
                TaskDTO taskDTO = DTOMockUtils.generateTask();
                taskDTO.setId(Long.parseLong(id));
                return taskDTO;
            }
        });
        when(taskManager.userTasksMap(anyString())).thenAnswer(new Answer<HashMap<String, ArrayList<TaskDTO>>>() {
            @Override
            public HashMap<String, ArrayList<TaskDTO>> answer(InvocationOnMock invocation) throws Throwable {
                String userId = (String)invocation.getArguments()[0];
                if (userId == null || userId.isEmpty()) throw new EmptyRequiredValueException();
                ArrayList<TaskDTO> taskList = DTOMockUtils.generateTasksList(TASK_QUANTITY);
                UserDTO user = DTOMockUtils.generateUser();
                user.setId(Long.parseLong(userId));
                if (TASK_QUANTITY < 1) return null;
                for (TaskDTO task : taskList) {
                    task.setUserDTO(user);
                }
                String projectName = taskList.get(0).getProjectDTO().getStory();
                HashMap<String, ArrayList<TaskDTO>> result = new HashMap<String, ArrayList<TaskDTO>>();
                result.put(projectName, taskList);
                return result;
            }
        });
    }

    @Test
    public void adminHome() throws Exception {
        USER_QUANTITY = 8;
        TASK_QUANTITY = 6;
        PROJECT_QUANTITY = 3;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin"));
        resultActions.andExpect(view().name("admin_page"))
                .andExpect(forwardedUrl("/WEB-INF/admin_page.jsp"))
                .andExpect(model().attributeExists(AdminController.USERS))
                .andExpect(model().attributeExists(AdminController.PROJECTS))
                .andExpect(model().attributeExists(AdminController.TASKS))
                .andExpect(model().attributeExists(AdminController.USER_DETAIL_RESOURCE))
                .andExpect(model().attributeExists(AdminController.CREATE_USER_RESOURCE))
                .andExpect(model().attributeExists(AdminController.PROJECT_DETAIL_RESOURCE))
                .andExpect(model().attributeExists(AdminController.CREATE_PROJECT_RESOURCE))
                .andExpect(model().attributeExists(AdminController.TASK_DETAIL_RESOURCE))
                .andExpect(model().attributeExists(AdminController.CREATE_TASK_RESOURCE))
                .andExpect(model().attributeExists(AdminController.BACK))
                .andExpect(model().attributeExists(AdminController.HOME))
                .andExpect(model().attribute(AdminController.USERS, hasSize(USER_QUANTITY)))
                .andExpect(model().attribute(AdminController.PROJECTS, hasSize(PROJECT_QUANTITY)))
                .andExpect(model().attribute(AdminController.TASKS, hasSize(TASK_QUANTITY)))
                .andExpect(model().attribute(AdminController.USER_DETAIL_RESOURCE, AdminController.USER_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.CREATE_USER_RESOURCE, AdminController.USER_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.PROJECT_DETAIL_RESOURCE, AdminController.PROJECT_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.CREATE_PROJECT_RESOURCE, AdminController.PROJECT_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.TASK_DETAIL_RESOURCE, AdminController.TASK_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.CREATE_TASK_RESOURCE, AdminController.TASK_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.BACK, "admin"))
                .andExpect(model().attribute(AdminController.HOME, AdminController.HOME_VALUE));
    }

    @Test
    public void createUser() throws Exception {
        ROLE_QUANTITY = 5;
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin/user"));
        resultActions.andExpect(view().name("create_user"))
                .andExpect(forwardedUrl("/WEB-INF/create_user.jsp"))
                .andExpect(model().attributeExists(AdminController.ROLES))
                .andExpect(model().attributeExists(AdminController.HOME))
                .andExpect(model().attributeExists(AdminController.MODIFY))
                .andExpect(model().attribute(AdminController.ROLES, hasSize(ROLE_QUANTITY)))
                .andExpect(model().attribute(AdminController.HOME, "admin"))
                .andExpect(model().attribute(AdminController.MODIFY, "user"));
    }

    @Test
    public void userDetail() throws Exception {
        UserDTO user = DTOMockUtils.generateUser();
        Long userId = user.getId();
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/4"));
        resultActions.andExpect(view().name(AdminController.USER_DETAIL_JSP))
                .andExpect(forwardedUrl("/WEB-INF/user_detail.jsp"))
                .andExpect(model().attributeExists(AdminController.USER))
                .andExpect(model().attribute(AdminController.USER, hasProperty("id", is(4L))))
                .andExpect(model().attributeExists(AdminController.MAP))
                .andExpect(model().attribute(AdminController.MAP, notNullValue()))
                .andExpect(model().attributeExists(AdminController.TASK_DETAIL_RESOURCE))
                .andExpect(model().attributeExists(AdminController.BUTTON_LABEL))
                .andExpect(model().attributeExists(AdminController.BACK))
                .andExpect(model().attribute(AdminController.BACK, ("/" + AdminController.USER_RESOURCES_PATH + "/" + 4)))
                .andExpect(model().attributeExists(AdminController.HOME))
                .andExpect(model().attribute(AdminController.HOME, AdminController.HOME_VALUE))
                .andExpect(model().attributeExists(AdminController.BUTTON_REDIRECTION_PAGE))
                .andExpect(model().attribute(AdminController.BUTTON_REDIRECTION_PAGE, AdminController.HOME_VALUE));

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/{userId}, 4"));
            Assert.fail("Expected behavior failed. Exception was expected since empty parameters were passed");
        } catch (Exception e) {}

    }

    @Test
    public void projectDetail() throws Exception{
        TASK_QUANTITY = 3;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin/project/1"));
        resultActions.andExpect(view().name(AdminController.PROJECT_DETAIL_JSP))
                        .andExpect(forwardedUrl("/WEB-INF/project_detail.jsp"))
                        .andExpect(model().attributeExists(AdminController.PROJECT))
                        .andExpect(model().attributeExists(AdminController.PROJECT_TASKS))
                        .andExpect(model().attribute(AdminController.PROJECT_TASKS, hasSize(TASK_QUANTITY)))
                        .andExpect(model().attributeExists(AdminController.TASK_DETAIL_RESOURCE))
                        .andExpect(model().attributeExists(AdminController.CREATE_TASK_RESOURCE))
                        .andExpect(model().attributeExists(AdminController.HOME))
                        .andExpect(model().attributeExists(AdminController.MODIFY))
                        .andExpect(model().attributeExists(AdminController.BACK))
                        .andExpect(model().attribute(AdminController.PROJECT, notNullValue()))
                        .andExpect(model().attribute(AdminController.PROJECT, hasProperty("id", is(1L))))
                        .andExpect(model().attribute(AdminController.TASK_DETAIL_RESOURCE, AdminController.TASK_RESOURCES_PATH))
                        .andExpect(model().attribute(AdminController.CREATE_TASK_RESOURCE, AdminController.PROJECT_RESOURCES_PATH + "/1/new_task"))
                        .andExpect(model().attribute(AdminController.HOME, "admin"))
                        .andExpect(model().attribute(AdminController.MODIFY, AdminController.PROJECT_RESOURCES_PATH))
                .andExpect(model().attribute(AdminController.BACK, "/" + AdminController.PROJECT_RESOURCES_PATH + "/" + 1));
    }

    @Test
    public void createProject() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin/project"));
        resultActions.andExpect(view().name("create_project"))
                .andExpect(forwardedUrl("/WEB-INF/create_project.jsp"))
                .andExpect(model().attributeExists(AdminController.HOME))
                .andExpect(model().attributeExists(AdminController.MODIFY))
                .andExpect(model().attribute(AdminController.HOME, "admin"))
                .andExpect(model().attribute(AdminController.MODIFY, AdminController.PROJECT_RESOURCES_PATH));

    }

    @Test
    public void modifyProject() throws Exception {
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/admin/project")
                .param(AdminController.PROJECT_STORY, "TestProject")
                .param(AdminController.PROJECT_DESCRIPTION, "TestProjectDescription")
                .param(AdminController.PROJECTID, "1"));
        resultActions.andExpect(view().name("redirect:/admin"));
    }

    @Test
    public void taskDetail() throws Exception {
        USER_QUANTITY = 4;
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin/task/3")
                .param(AdminController.BACK, "test_redirect"));
        resultActions.andExpect(model().attributeExists(AdminController.USERS))
                .andExpect(model().attribute(AdminController.USERS, hasSize(USER_QUANTITY)))
                .andExpect(model().attributeExists(AdminController.TASK))
                .andExpect(model().attribute(AdminController.TASK, hasProperty("id", is(3L))))
                .andExpect(model().attributeExists(AdminController.BACK))
                .andExpect(model().attributeExists(AdminController.HOME))
                .andExpect(model().attributeExists(AdminController.MODIFY))
                .andExpect(view().name(AdminController.TASK_DETAIL_JSP))
                .andExpect(forwardedUrl("/WEB-INF/task_detail.jsp"));
    }

    @Test
    public void createTask() throws Exception {
        USER_QUANTITY = 5;
        PROJECT_QUANTITY = 3;

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/admin/task"));
        resultActions.andExpect(model().attributeExists(AdminController.USERS))
                .andExpect(model().attribute(AdminController.USERS, hasSize(USER_QUANTITY)))
                .andExpect(model().attributeExists(AdminController.PROJECTS))
                .andExpect(model().attribute(AdminController.PROJECTS, hasSize(PROJECT_QUANTITY)))
                .andExpect(model().attributeExists(AdminController.HOME))
                .andExpect(model().attribute(AdminController.HOME, is("admin")))
                .andExpect(model().attributeExists(AdminController.MODIFY))
                .andExpect(view().name(AdminController.CREATE_TASK_JSP))
                .andExpect(forwardedUrl("/WEB-INF/create_task.jsp"));
    }
}
