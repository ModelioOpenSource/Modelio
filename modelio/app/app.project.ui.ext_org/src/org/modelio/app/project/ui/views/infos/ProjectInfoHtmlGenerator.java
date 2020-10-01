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

package org.modelio.app.project.ui.views.infos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.data.project.FragmentDescriptor;
import org.modelio.gproject.data.project.FragmentType;
import org.modelio.gproject.data.project.ModuleDescriptor;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.version.Version;
import org.modelio.version.ModelioVersion;
import org.osgi.framework.Bundle;

/**
 * Generator of project info html page.
 * <p>
 * Usage:
 * <ul>
 * <li>instantiate, this will run HTML generation.
 * <li>call {@link #getPageUrl()} to get the URL. The returned string is empty if generation failed.
 * </ul>
 * 
 * @author xzhang
 */
@objid ("0c2bbe76-0002-4344-8c7b-aa866d7c5dba")
class ProjectInfoHtmlGenerator {
    @objid ("5585c4cf-5138-4750-9db2-c08746cab9e2")
    private static final String INFO_CONTACT = "info.contact";

    @objid ("280ae74c-3d96-40f8-bcbe-a3a0bf993fbe")
    private static final String INFO_DESCRIPTION = "info.description";

    @objid ("d9021fb3-5eff-457b-9854-a7f3aefa804c")
    private static final String INFO_PROJECT_LOGO_NAME = "info.projetLogoName";

    @objid ("a160e516-df5a-468a-b677-c294dbf13eed")
    private String pageUrl;

    @objid ("bf90eafe-398d-4218-ac5d-99a9ed0cb2ac")
    private static final String INFO_LOGIN = "constellation.user.login";

    @objid ("ba33120b-7a74-49b7-aff5-277690f5d3e4")
    private ProjectAdapter projectAdapter;

    /**
     * Instantiate the generator and immediately generates the HTML page.
     * 
     * @param projectAdapter the project model
     */
    @objid ("f835672e-8d78-4c1e-8338-bd945dbde595")
    public ProjectInfoHtmlGenerator(ProjectAdapter projectAdapter) {
        this.projectAdapter = projectAdapter;
        createProjectInfoPageFromTemplate();
    }

    /**
     * @return the generated HTML page URL.
     */
    @objid ("a522bf63-233e-4c62-8e81-746c1f3093fd")
    public String getPageUrl() {
        return this.pageUrl;
    }

    @objid ("21d1cc76-5508-4eb2-8d6e-fcca7856e016")
    private String addProjectLogo() {
        String iconName = this.projectAdapter.getProperties().getValue(ProjectInfoHtmlGenerator.INFO_PROJECT_LOGO_NAME);
        if (iconName == null) {
            return "";
        }
        StringBuilder addIconString = new StringBuilder();
        if (iconName.startsWith("http")) {
            try {
                URI uri = new URI(iconName);
        
                addIconString.append("<img src=\"");
                addIconString.append(uri.toString());
                addIconString.append("\" height=\"64\" width=\"64\">");
            } catch (@SuppressWarnings ("unused") URISyntaxException e) {
                addIconString = new StringBuilder();
            }
        } else {
            try {
                java.nio.file.Path iconPath = this.projectAdapter.getProjectFileStructure().getProjectDataPath().resolve(iconName);
                if (Files.exists(iconPath)) {
                    addIconString.append("<img src=\"");
                    addIconString.append(iconPath.toString());
                    addIconString.append("\" height=\"64\" width=\"64\">");
                }
            } catch (@SuppressWarnings ("unused") InvalidPathException e) {
                addIconString = new StringBuilder();
            }
        }
        return addIconString.toString();
    }

    /**
     * Create Libraries Fragments table content Columns: Name, Version, Description
     * 
     * @param fragments @return
     */
    @objid ("fc59e2b0-cd2a-4f29-a20b-2c5aa6f9f312")
    private String createLibrariesTableContent(List<FragmentDescriptor> fragments) {
        StringBuilder content = new StringBuilder();
        for (FragmentDescriptor fragment : fragments) {
            content.append("<tr>");
        
            String addIconString = "<img src=\"" + getFragmentIconPath(fragment.getType()) + "\"> ";
            content.append("<td>");
            content.append(addIconString);
            content.append(fragment.getId());
            content.append("</td>");
        
            String fVersion = fragment.getProperties().getValue("FragmentVersion");
            content.append("<td>");
            content.append(fVersion == null ? "" : fVersion);
            content.append("</td>");
        
            content.append("<td>");
            String fDescription = fragment.getProperties().getValue("FragmentDescription");
            if (fDescription != null && fDescription.length() > 0) {
                content.append("<div class='librarieDescriptorTextarea'>");
                content.append(fDescription);
                content.append("</div>");
            }
            content.append("</td>");
        
            content.append("</tr>");
        }
        return content.toString();
    }

    /**
     * Create Modules table content Columns: Name, Version
     * 
     * @param modules @return
     */
    @objid ("25fe63e2-5bab-4c58-9a0a-659b45167b4b")
    private String createModulesTableContent(Object[] modules) {
        StringBuilder content = new StringBuilder();
        
        String addIconString = "<img src=\"" + getFragmentIconPath(FragmentType.MDA) + "\"> ";
        for (Object element : modules) {
            if (element instanceof ModuleDescriptor) {
                content.append("<tr>");
        
                content.append("<td>");
                content.append(addIconString);
                content.append(((ModuleDescriptor) element).getName());
                content.append("</td>");
        
                content.append("<td>");
                content.append(((ModuleDescriptor) element).getVersion().toString());
                content.append("</td>");
        
                content.append("</tr>");
            }
        }
        return content.toString();
    }

    @objid ("92efe150-1a02-488a-bb57-243ca2469354")
    private String createProjectInfoPageFromTemplate() {
        this.pageUrl = "";
        Bundle bundle = Platform.getBundle(AppProjectUi.PLUGIN_ID);
        
        // Get the template file
        IPath projectInfoPagePath = new Path("/res/projectInfo.html");
        URL templateUrl = FileLocator.find(bundle, projectInfoPagePath, null);
        File pageTemplate = null;
        try {
            String templatePathString = FileLocator.toFileURL(templateUrl).getPath();
            pageTemplate = new File(templatePathString);
        
            if (!pageTemplate.exists()) {
                AppProjectUi.LOG.error("'%s' Project Info html template does not exist.", pageTemplate);
                return "";
            }
        } catch (IOException e) {
            AppProjectUi.LOG.error("Error when getting the template file from url: %s", FileUtils.getLocalizedMessage(e));
            AppProjectUi.LOG.debug(e);
            return "";
        }
        
        // Get the .runtime file
        java.nio.file.Path runtimeProjectInfoPagePath = this.projectAdapter.getProjectDescriptor().getProjectFileStructure().getProjectRuntimePath().resolve("projectInfo.html");
        try {
            java.nio.file.Path parent = runtimeProjectInfoPagePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            } else {
                return "";
            }
        } catch (IOException e) {
            AppProjectUi.LOG.error("Cannot create .runtime directory: %s", FileUtils.getLocalizedMessage(e));
            AppProjectUi.LOG.debug(e);
            return "";
        }
        
        try {
            // copy template the the .runtime project dir
            Files.copy(pageTemplate.toPath(), runtimeProjectInfoPagePath, StandardCopyOption.REPLACE_EXISTING);
            // complete template
            fillProjectInfoPage(runtimeProjectInfoPagePath);
        
            this.pageUrl = runtimeProjectInfoPagePath.toString();
        } catch (IOException e) {
            AppProjectUi.LOG.error("Error copying file: %s", FileUtils.getLocalizedMessage(e));
            AppProjectUi.LOG.debug(e);
        }
        return this.pageUrl;
    }

    /**
     * Create Work Models Fragments table content Columns: Name, Type, Uri
     * 
     * @param fragments @return
     */
    @objid ("325e6f93-4795-4cea-9ea2-43c0595c7486")
    private String createWorkModelsTableContent(List<FragmentDescriptor> fragments) {
        StringBuilder content = new StringBuilder();
        for (FragmentDescriptor fragment : fragments) {
            content.append("<tr>");
        
            String addIconString = "<img src=\"" + getFragmentIconPath(fragment.getType()) + "\"> ";
            content.append("<td>");
            content.append(addIconString);
            content.append(fragment.getId());
            content.append("</td>");
        
            boolean isDistant = fragment.getType() == FragmentType.EXML_SVN;
            String fType = isDistant ? AppProjectUiExt.I18N.getString("ProjectInfoHtmlGenerator.workmodeltype.distant") : AppProjectUiExt.I18N.getString("ProjectInfoHtmlGenerator.workmodeltype.local");
            content.append("<td>");
            content.append(fType);
            content.append("</td>");
        
            String fUriString = isDistant ? fragment.getUri().toString().replaceAll("%20", " ") : "";
            content.append("<td>");
            content.append(fUriString);
            content.append("</td>");
        
            content.append("</tr>");
        }
        return content.toString();
    }

    @objid ("ee3ca389-09d4-463d-88bb-14f05b677df5")
    private void fillProjectInfoPage(java.nio.file.Path filePath) {
        String line = "";
        StringBuilder oldtext = new StringBuilder();
        
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8);) {
            while ((line = reader.readLine()) != null) {
                oldtext.append(line);
                oldtext.append(System.getProperty("line.separator"));
            }
            reader.close();
        } catch (IOException ioe) {
            AppProjectUi.LOG.error("Error when copying the template file: %s", FileUtils.getLocalizedMessage(ioe));
            AppProjectUi.LOG.debug(ioe);
        }
        
        // replace strings in a the template file
        StringBuilder newtext = replaceTemplateStrings(oldtext);
        getFilePathOf("/res/img/"); // force loading the img folder
        
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);) {
            writer.write(newtext.toString());
            writer.close();
        } catch (IOException ioe) {
            AppProjectUi.LOG.error("Error when writing the html page: %s", FileUtils.getLocalizedMessage(ioe));
            AppProjectUi.LOG.debug(ioe);
        }
    }

    /**
     * @return full file path
     */
    @objid ("1b71db05-b498-40dd-a5ad-1ce5597923e9")
    private String getFilePathOf(String fileName, String pluginId) {
        String path = "";
        Bundle bundle = Platform.getBundle(pluginId);
        String s = "platform:/plugin/" + bundle.getSymbolicName() + "/" + fileName; // To avoid the space in the bundle path
        URL url = null;
        try {
            url = new URL(s);
            path = FileLocator.toFileURL(url).getPath();
        } catch (IOException e) {
            AppProjectUi.LOG.debug("File path '%s' is not found: %s", s, FileUtils.getLocalizedMessage(e));
            AppProjectUi.LOG.error(e);
        }
        return "file://" + path; // add protocol
    }

    @objid ("4cde8c84-9bdd-420e-9d0e-14667556936d")
    private String getFragmentIconPath(FragmentType type) {
        String fileName;
        switch (type) {
        case EXML:
            fileName = "icons/exmlfragment.png";
            break;
        case EXML_URL:
            fileName = "icons/httpfragment.png";
            break;
        case EXML_SVN:
            fileName = "icons/svnfragment.png";
            break;
        case RAMC:
            fileName = "icons/ramcfragment.png";
            break;
        case MDA:
            fileName = "icons/mdafragment.png";
            break;
        default:
            fileName = "icons/undefinedfragment.png";
            break;
        }
        return getFilePathOf(fileName, CoreUi.PLUGIN_ID);
    }

    /**
     * @return the style to apply to modelio version string.
     */
    @objid ("b5d7d888-d7f8-435d-a736-557bd978a59e")
    private String getModelioVersionClass() {
        Version projVersion = this.projectAdapter.getProjectDescriptor().getModelioVersion();
        if (projVersion == null) {
            return "project_version_older";
        } else if (projVersion.isNewerThan(ModelioVersion.VERSION)) {
            return "project_version_newer";
        } else if (ModelioVersion.VERSION.equals(projVersion)) {
            return "project_version_same";
        } else {
            // project is older
            if (projVersion.getMajorVersion() == ModelioVersion.VERSION.getMajorVersion() &&
                    projVersion.getMinorVersion() == ModelioVersion.VERSION.getMinorVersion()) {
                // Only build is different
                return "project_version_same";
            } else {
                return "project_version_older";
            }
        }
    }

    @objid ("c6742097-e849-4b5b-a735-21b90bc1375e")
    private String getModelioVersionString() {
        Version modelioVersion = this.projectAdapter.getProjectDescriptor().getModelioVersion();
        
        if (modelioVersion == null) {
            return "<3.4.01";
        } else {
            return modelioVersion.toString();
        }
    }

    /**
     * Add javascript code to hide a container
     */
    @objid ("7fb376ad-6d04-4e17-befb-e788159280f7")
    private StringBuilder hideContainer(StringBuilder sb, String idToHide) {
        String hideString = "document.getElementById('" + idToHide + "').style.display = 'none';";
        return replaceAll(sb, "//windowOnLoadFunction", "//windowOnLoadFunction" + System.getProperty("line.separator") + hideString);
    }

    /**
     * Replace all the oldString by the newString in the given buffer.
     */
    @objid ("57b914f3-f528-487a-a81e-fe5e8ee3973f")
    private StringBuilder replaceAll(StringBuilder buffer, String oldString, String newString) {
        int index = buffer.indexOf(oldString);
        while (index != -1) // replace all
        {
            buffer.replace(index, index + oldString.length(), newString);
            index += newString.length(); // get the beginning index of the next replacement if need
            index = buffer.indexOf(oldString, index);
        }
        return buffer;
    }

    /**
     * Replace the string like [#stringKey#] by the string of the stringKey in the properties file Define all the string of the html template placed in the properties like [#stringKey#] (must have the same 'stringKey' in the properties file)
     */
    @objid ("2469703a-ae11-4041-ab9e-79b14f527610")
    private StringBuilder replaceTemplateFixContent(StringBuilder sb) {
        Pattern myPattern = Pattern.compile("\\[\\#([^\\]]*)\\#\\]");
        Matcher m = myPattern.matcher(sb.toString());
        while (m.find()) {
            String toReplace = m.group(1);
            String i18nKey = "$" + toReplace;
            replaceAll(sb, "[#" + toReplace + "#]", AppProjectUiExt.I18N.getString(i18nKey));
        }
        return sb;
    }

    @objid ("a0abcec7-4fc0-4cc5-ada6-022c185345eb")
    private StringBuilder replaceTemplateStrings(StringBuilder source) {
        // Replace all the fix content of source file
        replaceTemplateFixContent(source);
        
        // Add css files
        String[] cssList = {
                "/res/bootstrap.min.css",
                "/res/projectInfo.css"
        };
        replaceAll(source, "$css_files", createCssList(cssList));
        
        // Add javascript files
        String[] javascriptList = {
                "/res/projectInfo.js",
                "/res/jquery-3.3.1.slim.min.js",
                "/res/bootstrap.bundle.min.js"
        };
        replaceAll(source, "$javascript_files", createJavascriptList(javascriptList));
        
        // Add project icon
        replaceAll(source, "$project_icon", addProjectLogo());
        
        // Add project name
        replaceAll(source, "$project_name", this.projectAdapter.getName());
        
        // Add body font family/size
        Font font = Display.getDefault().getSystemFont();
        String fontFamily = font.getFontData()[0].getName();
        int fontSize = font.getFontData()[0].getHeight();
        replaceAll(source, "$body_font_family", fontFamily);
        replaceAll(source, "$body_font_size", Integer.toString(fontSize) + "pt");
        
        // Add project description (double white chars are interpreted as line breaks)
        replaceAll(source, "$project_description",
                this.projectAdapter.getProperties().getValue(ProjectInfoHtmlGenerator.INFO_DESCRIPTION, "").replaceAll("[ ]{2,}", "<br/>").replaceAll("\\xA0{2,}", "<br/>-"));
        
        // Basic info
        String contactName = this.projectAdapter.getProperties().getValue(ProjectInfoHtmlGenerator.INFO_CONTACT, "");
        replaceAll(source, "$project_contact", contactName);
        replaceAll(source, "$mail_subject", this.projectAdapter.getName().replaceAll(" ", "%20"));
        replaceAll(source, "$project_storage_path", this.projectAdapter.getStoragePathString());
        replaceAll(source, "$project_storage_last_modification_time", this.projectAdapter.getStorageLastModificationTimeString());
        
        if (this.projectAdapter.getProjectDescriptor().getRemoteLocation() != null) {
            // Display constellation properties
            replaceAll(source, "$project_display_constellation_row", "table-row");
        
            String remoteLocation = this.projectAdapter.getProjectDescriptor().getRemoteLocation();
            replaceAll(source, "$project_remote_location", remoteLocation);
            replaceAll(source, "$project_login", this.projectAdapter.getProperties().getValue(ProjectInfoHtmlGenerator.INFO_LOGIN, ""));
        } else {
            // Hide constellation properties
            replaceAll(source, "$project_display_constellation_row", "none");
        }
        
        if (contactName == null || contactName.isEmpty()) {
            replaceAll(source, "$display_sendMail_icon", "none");
        } else {
            replaceAll(source, "$display_sendMail_icon", "block");
        }
        
        // Project modelio version
        replaceAll(source, "$project_modelio_version_str", getModelioVersionString());
        replaceAll(source, "$project_modelio_version_class", getModelioVersionClass());
        
        // WorkModels Fragments section
        List<FragmentDescriptor> workModelsFragments = this.projectAdapter.getWorkModelsFragments();
        if (workModelsFragments.size() > 0) {
            replaceAll(source, "$tbl_workModels_content", createWorkModelsListContent(workModelsFragments));
        } else { // Hide container if no WorkModels
            hideContainer(source, "AccordionContainerWorkModels");
        }
        // Libraries Fragments section
        List<FragmentDescriptor> librariesFragments = this.projectAdapter.getLibrariesFragments();
        if (librariesFragments.size() > 0) {
            replaceAll(source, "$tbl_libraries_content", createLibrariesListContent(librariesFragments));
        } else { // Hide container if no Libraries
            hideContainer(source, "AccordionContainerLibraries");
        }
        
        // Modules section
        replaceAll(source, "$tbl_modules_content", createModulesListContent(this.projectAdapter.getModules()));
        return source;
    }

    @objid ("62f0ca14-a858-4d0b-bf42-853f31f287f2")
    private String getFilePathOf(String fileName) {
        return getFilePathOf(fileName, AppProjectUi.PLUGIN_ID);
    }

    @objid ("fd9d7711-738a-4652-baff-b8c4821da383")
    private String createLibrariesListContent(List<FragmentDescriptor> fragments) {
        StringBuilder content = new StringBuilder();
        for (FragmentDescriptor fragment : fragments) {
            content.append("<div class='listitem'>");
            String fDescription = fragment.getProperties().getValue("FragmentDescription");
            if (fDescription != null && fDescription.length() > 0) {
                content.append("<span data-toggle=\"tooltip\" data-html=\"true\" title=\"" + fDescription + "\">");
            } else {
                content.append("<span>");
            }
        
            String addIconString = "<img src=\"" + getFragmentIconPath(fragment.getType()) + "\"> ";
            content.append(addIconString);
            content.append(fragment.getId());
            content.append("</span>");
            content.append("</div>");
        }
        return content.toString();
    }

    @objid ("b76b4f70-9397-49d6-a11f-42344da1da4b")
    private String createModulesListContent(Object[] modules) {
        StringBuilder content = new StringBuilder();
        
        String addIconString = "<img src=\"" + getFragmentIconPath(FragmentType.MDA) + "\"> ";
        for (Object element : modules) {
            if (element instanceof ModuleDescriptor) {
                content.append("<div class='listitem'>");
                content.append(addIconString);
                content.append(((ModuleDescriptor) element).getName() + " " + ((ModuleDescriptor) element).getVersion().toString());
                content.append("</div>");
            }
        }
        return content.toString();
    }

    @objid ("b0a2d06f-9369-4ac6-b923-5d1ffa2b2f76")
    private String createWorkModelsListContent(List<FragmentDescriptor> fragments) {
        StringBuilder content = new StringBuilder();
        for (FragmentDescriptor fragment : fragments) {
            content.append("<div class='listitem'>");
            boolean isDistant = fragment.getType() == FragmentType.EXML_SVN;
            String fUriString = isDistant ? fragment.getUri().toString().replaceAll("%20", " ") : "";
            if (fUriString != null && fUriString.length() > 0) {
                content.append("<span data-toggle=\"tooltip\" data-html=\"true\" title=\"" + fUriString + "\">");
            } else {
                content.append("<span>");
            }
            String addIconString = "<img src=\"" + getFragmentIconPath(fragment.getType()) + "\"> ";
            content.append(addIconString);
            content.append(fragment.getId());
            content.append("</span>");
            content.append("</div>");
        }
        return content.toString();
    }

    @objid ("05a3cb56-62a8-49cd-a3b5-80b4930558ab")
    private String createCssList(String[] cssArray) {
        StringBuilder cssList = new StringBuilder();
        
        for (String element : cssArray) {
            cssList.append("<link rel='stylesheet' href='" + getFilePathOf(element) + "' type='text/css'></link>");
            cssList.append(System.getProperty("line.separator"));
        }
        return cssList.toString();
    }

    @objid ("b4196f48-fbfe-48e6-a2d7-c2454064e936")
    private String createJavascriptList(String[] javascriptArray) {
        StringBuilder JsList = new StringBuilder();
        
        for (String element : javascriptArray) {
            JsList.append("<script src='" + getFilePathOf(element) + "' type='text/javascript'></script>");
            JsList.append(System.getProperty("line.separator"));
        }
        return JsList.toString();
    }

}
