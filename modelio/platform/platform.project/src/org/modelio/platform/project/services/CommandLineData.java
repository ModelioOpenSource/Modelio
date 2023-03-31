/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.modelio.platform.project.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.project.plugin.AppProjectCore;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.files.FileUtils;

/**
 * Modelio command line arguments.
 * 
 * 
 * @author phv
 */
@objid ("acd57237-7ee2-4420-a2b2-ac6a3a3f09c7")
public class CommandLineData {
    @objid ("40df4870-3ad5-453c-9139-26461d6f1554")
    private static final String BATCH_OPTION = "batch";

    @objid ("2faf65a2-5e9b-4b82-9e68-c5b37661031b")
    private static final String CREATE_OPTION = "create";

    @objid ("2486b907-44de-4ae4-b041-fc782a106cba")
    private static final String JOIN_OPTION = "join";

    @objid ("7fa703e5-76d2-4ba1-aade-8442981ed327")
    private static final String MDEBUG_OPTION = "mdebug";

    @objid ("8ba2f566-36e9-47a8-bc42-267f0b14673e")
    private static final String OPEN_OPTION = "open";

    /**
     * Batch parameter, followed by to arguments.
     */
    @objid ("a1e3682c-3ef5-4126-a182-196c1071240b")
    private static final String PARAM_OPTION = "param";

    /**
     * Modelio user password.
     */
    @objid ("b3e28199-c348-46f7-bc85-0ff8785ca634")
    private static final String PASSWORD_OPTION = "mpassword";

    /**
     * Modelio server URL option
     */
    @objid ("e232c565-1b24-4f62-95c5-5d4d201dc461")
    private static final String MSERVER_OPTION = "mserver";

    /**
     * Modelio client ID option
     */
    @objid ("ba958bc2-9e8b-4426-b592-32a7f85aa1b6")
    private static final String MCLIENTID_OPTION = "mclientid";

    /**
     * Modelio client secret option
     */
    @objid ("675e725e-acb1-4a9c-8ddf-a39b324d29a8")
    private static final String MCLIENTSECRET_OPTION = "mclientsecret";

    @objid ("d1261b0b-ce83-490a-8ced-8d345d34ee53")
    private static final String PROJECT_OPTION = "project";

    @objid ("16213bdb-f898-4ea6-a256-3cf9e4fad233")
    private static final String TEMPLATE_OPTION = "template";

    /**
     * Options file : a .properties file containing user, password and server.
     */
    @objid ("b70f0d37-cad3-4d3b-a70c-0a6b12a80378")
    private static final String PARAMS_FILE_OPTION = "optionsfile";

    /**
     * Modelio user login.
     * <p>
     * Cannot use '-user', it is already used by Eclipse framework.
     */
    @objid ("4a1c0947-829e-47cb-b3f6-d2641538ea93")
    private static final String USER_OPTION = "muser";

    /**
     * Modelio user API token.
     */
    @objid ("b7c5ea3e-4582-41e7-b420-3602c07592ea")
    private static final String USER_TOKEN_OPTION = "musertoken";

    @objid ("b5265f8e-f29e-4c19-8bd6-1aff53ff405a")
    private static final String WORKSPACE_OPTION = "workspace";

    @objid ("f56f34a4-f175-42c4-8ae4-4d83fd356ca8")
    private final Map<String, String> batchParams = new HashMap<>();

    @objid ("58514b1f-a34a-4b60-8d66-133e0260509a")
    private boolean createProject = false;

    
    @mdl.prop
    @objid ("92c45bac-a113-4a57-8392-439e30343384")
    private boolean debug;

    @mdl.propgetter
    public boolean isDebug() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.debug;
    }

    @objid ("140f173c-e34c-4a00-88e6-558df7f52b04")
    private boolean emptyBatch = true;

    @objid ("44a6988a-5b00-4b1d-9997-f6fa0c330663")
    private boolean joinProject;

    @objid ("7d4b6c76-d828-49eb-847c-5dbe0e050669")
    private boolean openProject = false;

    
    @mdl.prop
    @objid ("46eb6b7a-dcfb-42f4-b316-991d6cf48e36")
    private String password = null;

    @mdl.propgetter
    public String getPassword() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.password;
    }

    
    @mdl.prop
    @objid ("052ba9e3-ac78-4ffc-9e9d-57f28c31ba01")
    private String projectName = null;

    @mdl.propgetter
    public String getProjectName() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.projectName;
    }

    
    @mdl.prop
    @objid ("bcf181a3-293e-4a63-9207-f2a3c7f49d95")
    private String script = null;

    @mdl.propgetter
    public String getScript() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.script;
    }

    /**
     * The template to use to create the project.
     */
    
    @mdl.prop
    @objid ("358dac90-7485-452a-a734-daef72638ebe")
    private String template = null;

    @mdl.propgetter
    public String getTemplate() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.template;
    }

    /**
     * The user login
     */
    
    @mdl.prop
    @objid ("585c1c1e-cee6-4223-964c-30cfb9b69477")
    private String user = null;

    @mdl.propgetter
    public String getUser() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.user;
    }

    /**
     * The workspace path
     */
    
    @mdl.prop
    @objid ("4c6c622f-97ad-4012-b56a-b9c31a9cd6e4")
    private String workspace = null;

    @mdl.propgetter
    public String getWorkspace() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.workspace;
    }

    /**
     * The user login
     */
    
    @mdl.prop
    @objid ("0b69c4c5-a87b-47dd-97a2-2e094d1d5e2f")
    private String modelioClientId = null;

    @mdl.propgetter
    public String getModelioClientId() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.modelioClientId;
    }

    /**
     * The user login
     */
    
    @mdl.prop
    @objid ("00199343-940f-405c-9f80-136e4af06e45")
    private String modelioClientSecret = null;

    @mdl.propgetter
    public String getModelioClientSecret() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.modelioClientSecret;
    }

    /**
     * The user login
     */
    
    @mdl.prop
    @objid ("d615c890-e693-422f-9602-b9c1d343cb13")
    private String userToken = null;

    @mdl.propgetter
    public String getUserToken() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.userToken;
    }

    @objid ("97d265a9-7974-4c4c-8125-8ce3611553dc")
    private URI serverUri;

    @objid ("8b8dec69-9935-4e72-b726-4b525f698259")
    private IAuthData authData;

    /**
     * C'tor
     * @param args the command line arguments
     * @throws IllegalArgumentException if the command line is invalid
     */
    @objid ("df8b6edb-1fab-478f-a8ce-81850b7732e6")
    public  CommandLineData(String[] args) throws IllegalArgumentException {
        parse(args);
    }

    /**
     * Get the batch parameters.
     * @return batch parameters.
     */
    @objid ("4fdfb664-311b-4089-88bb-b90063c00be0")
    public Map<String, String> getBatchParameters() {
        return this.batchParams;
    }

    /**
     * @return <i>true</i> if batch mode else <i>false</i>.
     */
    @objid ("fa50daad-f574-410c-9615-3e3588c44e20")
    public boolean isBatch() {
        return this.projectName != null && this.script != null;
    }

    /**
     * @return <i>true</i> if the project is to be created else <i>false</i>.
     */
    @objid ("0b10bee0-f483-48fa-a1f7-0cb8843600fc")
    public boolean isCreate() {
        return this.createProject;
    }

    /**
     * @return <i>true</i> if batch mode but there is nothing to do.
     */
    @objid ("8943438c-bdd0-44ff-baa8-10ad28be47b1")
    public boolean isEmpty() {
        return this.emptyBatch;
    }

    /**
     * @return true if a Constellation project is to be joined.
     */
    @objid ("7724ebc5-d602-4c80-9d63-2118e86039a3")
    public boolean isJoin() {
        return this.joinProject;
    }

    /**
     * @return true if a project is to open.
     */
    @objid ("973e9f6e-3819-4ad4-a985-735bc7095c16")
    public boolean isOpen() {
        return this.openProject;
    }

    @objid ("53df1e39-e8ab-4903-9cbe-97e11dfa8b7b")
    @Override
    public String toString() {
        return "project=" + this.projectName + " create=" + this.createProject + " workspace=" + this.workspace;
    }

    /**
     * Check the command line arguments contains <i>nbRequired</i> more arguments to match an argument parameter.
     * @param args the command line arguments
     * @param index index of the parameterized argument
     * @param nbRequired count of required more arguments for the argument <i>args[index]</i>
     */
    @objid ("927f5681-348b-4c67-b3a7-274839b8a2fd")
    private void checkSupArg(String[] args, int index, int nbRequired) throws IllegalArgumentException {
        if (args.length <= index + nbRequired) {
            String msg = AppProjectCore.I18N.getMessage("CommandlineData.ArgNeedParameter",
                    Arrays.toString(args),
                    args[index],
                    nbRequired);
            throw new IllegalArgumentException(msg);
        }
        
        for (int i=1; i<= nbRequired; i++) {
            if (args[index+i].startsWith("-")) {
                // Next arg is another option
                String msg = AppProjectCore.I18N.getMessage("CommandlineData.ArgNeedParameter",
                        Arrays.toString(args),
                        args[index+i],
                        nbRequired);
                throw new IllegalArgumentException(msg);
            }
        }
        
    }

    /**
     * @return the Modelio Server URI.
     */
    @objid ("7339f56f-ca4a-4715-8921-0dec4aefc0d1")
    public URI getServerUri() {
        return this.serverUri;
    }

    /**
     * Parse and validate Modelio server URL.
     * <p>
     * Ensure it is a valid URI and check the scheme is null or 'https'.
     * @param serverUrlStr the URL to parse
     * @return the parsed URI
     * @throws URISyntaxException if the URL is invalid.
     */
    @objid ("3cbe87e6-5a0f-49e3-8d9f-cae4e8d68659")
    public static URI validateServerUrl(String serverUrlStr) throws URISyntaxException {
        URI uri = new URI (serverUrlStr);
        if (uri.getScheme() == null || uri.getScheme().equals("https"))
            return uri;
        throw new URISyntaxException(serverUrlStr, "Server URL scheme must be 'https' or not specified.", 0);
        
    }

    @objid ("5a7a26e6-7a1f-4c79-a230-7b818ad6ba4b")
    private void parseOptionsFile(String pathStr) throws IOException {
        Properties props = new Properties();
        Path p = Paths.get(pathStr);
        
        try (var is = Files.newBufferedReader(p, Charset.defaultCharset())) {
            props.load(is);
        }
        
        for (Entry<Object, Object> en: props.entrySet()) {
            String key = (String) en.getKey();
            String value = (String) en.getValue();
            try {
                parseOption( key, value);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException(String.format("Invalid '%s'='%s' option in '%s' file: %s", key, value, pathStr, e.getLocalizedMessage()));
            }
        }
        
    }

    @objid ("e72d749b-da0e-4797-98cd-12eb397f6d39")
    private void parseOption(String key, String value) {
        switch (key) {
        case USER_OPTION:
            this.user = value;
            this.emptyBatch = false;
            break;
        case PASSWORD_OPTION:
            this.password = value;
            this.emptyBatch = false;
            break;
        case USER_TOKEN_OPTION:
            this.userToken = value;
            this.emptyBatch = false;
            break;
        case MCLIENTID_OPTION:
            this.modelioClientId = value;
            this.emptyBatch = false;
            break;
        case MCLIENTSECRET_OPTION:
            this.modelioClientSecret = value;
            this.emptyBatch = false;
            break;
        case MSERVER_OPTION:
            try {
                this.serverUri = validateServerUrl (value);
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException(String.format("'%s' is not valid server URL: %s", value, e.getLocalizedMessage()));
            }
            break;
        case PROJECT_OPTION:
        case OPEN_OPTION:
            this.openProject = true;
            this.projectName = value;
            this.emptyBatch = false;
            break;
        case BATCH_OPTION:
            this.script = value;
            this.emptyBatch = false;
            break;
        case WORKSPACE_OPTION:
            this.workspace = value;
            this.emptyBatch = false;
            break;
        case CREATE_OPTION:
            this.createProject = true;
            this.projectName = value;
            this.emptyBatch = false;
            break;
        case JOIN_OPTION:
            this.joinProject = true;
            this.projectName = value;
            this.emptyBatch = false;
            break;
        case TEMPLATE_OPTION:
            this.template = value;
            this.emptyBatch = false;
            break;
        case PARAMS_FILE_OPTION:
            try {
                parseOptionsFile(value);
            } catch (IOException e) {
                throw new IllegalArgumentException(String.format("Invalid '%s' options file: %s", value, FileUtils.getLocalizedMessage(e)));
            }
            break;
        case MDEBUG_OPTION:
            this.debug = true;
            break;
        default:
            final String paramPrefix = PARAM_OPTION+".";
            if (key.startsWith(paramPrefix)) {
                this.batchParams.put(key.substring(paramPrefix.length()), value);
            } else {
                throw new IllegalArgumentException(String.format("Unknown option: %s", key));
            }
        }
        
    }

    @objid ("118f3313-13e8-45d7-bae3-e63c3e13e4f8")
    private void parse(String[] args) {
        // Parse command line arguments for a batch run
        int i = 0;
        
        while (i < args.length) {
            String argi = args[i];
            if (! argi.startsWith("-")) {
                AppProjectCore.LOG.warning("Unknown '%s' argument at index %d, ignored.", argi, i);
                // Also print to stderr for batch mode reporting
                System.err.printf("Unknown '%s' argument at index %d, ignored.\n", argi, i);
                i++;
                continue;
            }
            argi = argi.substring(1);
        
            switch (argi) {
            case PARAMS_FILE_OPTION:
            case PROJECT_OPTION:
            case OPEN_OPTION:
            case BATCH_OPTION:
            case WORKSPACE_OPTION:
            case USER_OPTION:
            case USER_TOKEN_OPTION:
            case PASSWORD_OPTION:
            case MSERVER_OPTION:
            case MCLIENTID_OPTION:
            case MCLIENTSECRET_OPTION:
            case CREATE_OPTION:
            case JOIN_OPTION:
            case TEMPLATE_OPTION:
                checkSupArg(args, i, 1);
                parseOption(argi, args[i + 1]);
                i += 2;
                break;
            case MDEBUG_OPTION:
                this.debug = true;
                i += 1;
                break;
            case PARAM_OPTION:
                checkSupArg(args, i, 2);
                this.batchParams.put(args[i + 1], args[i + 2]);
                i += 3;
                break;
            default:
                i++;
                break;
            }
        }
        
    }

    @objid ("9c49b3ec-b52d-42ba-88ba-d52312fdc53f")
    public IAuthData getAuthData() {
        if (this.authData == null) {
            // Can't use the UserToken in here, assume it's a user/password auth
            this.authData = new UserPasswordAuthData(getUser(), getPassword(), false);
        }
        return this.authData;
    }

    @objid ("ff605ed4-0ba6-4321-8c57-27f6fcbc48a8")
    public void setAuthData(IAuthData authData) {
        this.authData = authData;
    }

}
