package tp.config;

import java.io.InputStream;
import java.util.Properties;

import com.despegar.automation.commons.config.contextappmanager.AbstractExecutionContextAppManager;
import com.despegar.automation.commons.config.model.GitResources;
import com.despegar.automation.commons.config.model.ResourcesRepo;
import com.despegar.automation.commons.utils.QCFileUtils;

public class ExecutionContextAppManager
    extends AbstractExecutionContextAppManager {

    private static String MAIN_DIRECTORY = "config/lschmidt/";

    public ExecutionContextAppManager(ApplicationCode applicationCode) {
        super(applicationCode.getCode(), Boolean.TRUE);
    }

    @Override
    protected ResourcesRepo setEnvironmentsPath() {
        GitResources gitResources = this.buildGitResources();
        gitResources.setFolderResourceGit(MAIN_DIRECTORY + this.applicationCode + "/environments");
        return gitResources;
    }

    @Override
    protected ResourcesRepo setOfficesDataPath() {
        GitResources gitResources = this.buildGitResources();
        gitResources.setFolderResourceGit(MAIN_DIRECTORY + this.applicationCode + "/officesdata");
        return gitResources;
    }

    @Override
    protected ResourcesRepo setTestsDataPath() {
        GitResources gitResources = this.buildGitResources();
        gitResources.setFolderResourceGit(MAIN_DIRECTORY + this.applicationCode + "/testsdata");
        return gitResources;
    }

    private GitResources buildGitResources() {
        InputStream inputStream = this.getClass().getResourceAsStream("/support/environment.properties");
        Properties props = QCFileUtils.loadProperties(inputStream, "ISO-8859-1");
        String repoGit = (String) props.get("resourcesRepoGit");
        String pathParentFolderNameGit = (String) props.get("workSpaceGit");
        String branchNameGit = (String) props.get("branchNameGit");

        GitResources gitResources = new GitResources();
        gitResources.setGitRepo(repoGit);
        gitResources.setBranchName(branchNameGit);
        gitResources.setPathParentFolderName(pathParentFolderNameGit);
        return gitResources;
    }

}
