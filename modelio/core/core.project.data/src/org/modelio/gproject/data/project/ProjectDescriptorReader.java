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
package org.modelio.gproject.data.project;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import net.sf.practicalxml.DomUtil;
import net.sf.practicalxml.ParseUtil;
import net.sf.practicalxml.XmlException;
import org.modelio.gproject.data.plugin.GProjectData;
import org.modelio.gproject.data.project.todo.InstallModuleDescriptor;
import org.modelio.gproject.data.project.todo.RemoveModuleDescriptor;
import org.modelio.gproject.data.project.todo.TodoActionDescriptor;
import org.modelio.gproject.data.project.todo.UpdateModuleDescriptor;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.ServerUserPassAuthData;
import org.modelio.vbasic.auth.SshAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.log.Log;
import org.modelio.vbasic.version.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * Read a project descriptor from an XML file.
 */
@objid ("cf0fcec4-99bb-11e1-ac83-001ec947ccaf")
public class ProjectDescriptorReader {
    @objid ("0625bb57-3019-11e2-8f81-001ec947ccaf")
    private DefinitionScope forcedScope;

    /**
     * Default scope if no scope specified in file nor forces scope.
     */
    @objid ("c87e0610-5a62-4a82-836e-8bd9c452c058")
    private DefinitionScope defaultScope = null;

    @objid ("eedc2206-9a71-11e1-ac83-001ec947ccaf")
    private ProjectDescriptor projectDesc;

    @objid ("2db7d8e7-070d-11e2-9469-001ec947ccaf")
    private Path localProjectPath;

    /**
     * C'tor.
     */
    @objid ("eedc2207-9a71-11e1-ac83-001ec947ccaf")
    public  ProjectDescriptorReader() {
        
    }

    /**
     * Read a project descriptor from an XML input source.
     * @param is the XML input source.
     * @param scope the definition scope of the file.
     * If non <code>null</code> all elements will be assumed of the given scope.
     * If <code>null</code>, the scope will be read from the file.
     * @return the read descriptor.
     * @throws IOException in case of failure
     */
    @objid ("cd325526-2eb6-413d-bc3a-53cbf1f3b77e")
    public ProjectDescriptor read(final InputSource is, DefinitionScope scope) throws IOException {
        this.projectDesc = new ProjectDescriptor();
        this.forcedScope = scope;
        
        String confLocation = is.getSystemId();
        try {
            Document dom = ParseUtil.parse(is);
        
            decodeProject(dom.getDocumentElement());
        
        } catch (XmlException e) {
            throw new IOException("Parsing of '" + confLocation + "' failed: " + e.getCause().getLocalizedMessage(), e);
        } catch (IOException e) {
            throw new IOException("Parsing of '" + confLocation + "' failed: " + FileUtils.getLocalizedMessage(e), e);
        } catch (RuntimeException e) {
            throw new IOException("Parsing of '" + confLocation + "' unexpectedly failed: " + e.toString(), e);
        }
        return this.projectDesc;
    }

    /**
     * Read a project descriptor from an XML file.
     * <p>
     * Looks for an {@link ProjectDescriptorWriter#CONF_ENCRYPT_FILE} file in the same directory
     * to determine encryption of the file.
     * @param confFile The project.conf file path
     * <code>null</code> means the project is in the conf file directory.
     * @param scope the definition scope of the file.
     * If non <code>null</code> all elements will be assumed of the given scope.
     * If <code>null</code>, the scope will be read from the file.
     * @return the read descriptor.
     * @throws IOException in case of failure
     */
    @objid ("eedc2209-9a71-11e1-ac83-001ec947ccaf")
    public ProjectDescriptor read(final Path confFile, DefinitionScope scope) throws IOException {
        this.localProjectPath = confFile.getParent();
        
        // Look for encryption file
        String encryption = "";
        Path encryptionFile = this.localProjectPath.resolve(ProjectDescriptorWriter.CONF_ENCRYPT_FILE);
        if (Files.isRegularFile(encryptionFile)) {
            encryption = new String(Files.readAllBytes(encryptionFile), StandardCharsets.UTF_8);
        }
        
        // Setup input stream and run parsing
        try (InputStream fis = Files.newInputStream(confFile);
                InputStream is = getDecryptedInputStream(fis, encryption)) {
            InputSource xmlSource = new InputSource(is);
            xmlSource.setSystemId(confFile.toUri().toString());
            return read(xmlSource, scope);
        }
        
    }

    /**
     * Set the scope to set to read descriptor elements if no scope is specified either
     * in the source nor in the call to {@link #read(InputSource, DefinitionScope)}.
     * @param defaultScope the default scope.
     * @return <code>this</code> for convenience.
     */
    @objid ("c4a2ae95-e13d-47fb-bde8-ff8e4697fa05")
    public ProjectDescriptorReader setDefaultScope(DefinitionScope defaultScope) {
        this.defaultScope = defaultScope;
        return this;
    }

    /**
     * Convert the project descriptor DOM tree to the current version.
     * @see ProjectDescriptor#serialVersionUID
     * @param p the project descriptor DOM element
     * @param lversion the read format version
     * @throws IOException in case of failure
     */
    @objid ("eedc2211-9a71-11e1-ac83-001ec947ccaf")
    @SuppressWarnings("static-method")
    private void convertProject(final Element p, final long lversion) throws IOException {
        if (lversion > ProjectDescriptor.serialVersionUID) {
            throw new IOException(GProjectData.I18N.getMessage("ProjectDescriptorReader.format.newer", lversion, ProjectDescriptor.serialVersionUID));
        } else if (lversion == 1) {
            p.setAttribute("type", "LOCAL");
        } else if (lversion == 2) {
            // p.setAttribute("modelioVersion", "???");
            // Modelio <= 3.4.0
        } else if (lversion <= 5) {
            // compatible: nothing to do here
        } else {
            throw new IOException(GProjectData.I18N.getMessage("ProjectDescriptorReader.format.unsupported", lversion, ProjectDescriptor.serialVersionUID));
        }
        
    }

    @objid ("a78bd7c4-abbc-11e1-8392-001ec947ccaf")
    private FragmentDescriptor decodeFragment(final Element domEl) throws IOException {
        FragmentDescriptor fd = new FragmentDescriptor();
        
        fd.setId(domEl.getAttribute("id"));
        fd.setType(ProjectDescriptorReader.decodeFragmentType(domEl));
        fd.setProperties(decodeProperties(domEl));
        
        String stringUri = domEl.getAttribute("uri");
        if (!ProjectDescriptorReader.isEmpty(stringUri)) {
            try {
        
                fd.setUri(new URI(stringUri));
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }
        }
        
        fd.setAuthDescriptor(decodeAuth(domEl));
        fd.setScope(decodeScope(domEl, fd.getUri() == null ? null : this.defaultScope));
        return fd;
    }

    @objid ("eedc220e-9a71-11e1-ac83-001ec947ccaf")
    private void decodeProject(final Element p) throws IOException {
        // Check version
        String version = p.getAttribute("version");
        long lversion = Long.parseLong(version);
        if (lversion != ProjectDescriptor.serialVersionUID) {
            convertProject(p, lversion);
        }
        
        // Read name
        this.projectDesc.setName(p.getAttribute("name"));
        
        // Read project type
        this.projectDesc.setType(p.getAttribute("type"));
        
        
        // Read project path if specified
        String projPath = p.getAttribute("path");
        if (!ProjectDescriptorReader.isEmpty(projPath)) {
            this.projectDesc.setPath(Paths.get(projPath));
        } else {
            this.projectDesc.setPath(this.localProjectPath);
        }
        
        // Read project remote path if specified
        String remotePath = p.getAttribute("remote");
        if (!ProjectDescriptorReader.isEmpty(remotePath)) {
            this.projectDesc.setRemoteLocation(remotePath);
        }
        
        // since 3.4.1 : read Modelio version if specified
        String sv = p.getAttribute("modelioVersion");
        if (!ProjectDescriptorReader.isEmpty(sv)) {
            this.projectDesc.setModelioVersion(new Version(sv));
        }
        
        // Since 3.7 : read project space version
        String sProjSpaceVersion = p.getAttribute("projectSpaceVersion");
        if (!ProjectDescriptorReader.isEmpty(sProjSpaceVersion)) {
            this.projectDesc.setProjectSpaceVersion(Long.parseLong(sProjSpaceVersion));
        } else {
            this.projectDesc.setProjectSpaceVersion(0);
        }
        
        // Read fragments
        for (Element f : DomUtil.getChildren(p, "fragment")) {
            this.projectDesc.getFragments().add(decodeFragment(f));
        }
        
        // Read modules
        for (Element f : DomUtil.getChildren(p, "module")) {
            this.projectDesc.getModules().add(decodeModule(f));
        }
        
        for (Element cat : DomUtil.getChildren(p, "resources")) {
            for (Element f : DomUtil.getChildren(cat, "resource")) {
                this.projectDesc.getSharedResources().add(decodeResource(f));
            }
        }
        
        this.projectDesc.setAuthDescriptor(decodeAuth(p));
        this.projectDesc.setProperties(decodeProperties(p));
        
        // Read to-do
        for (Element f : DomUtil.getChildren(p, "todo")) {
            decodeTodo(f);
        }
        
    }

    /**
     * Reads the &lt;properties> tag and returns its content.
     * @param domNode the &lt;properties> DOM element.
     * @return the read properties
     * @throws IOException in case of parse error
     */
    @objid ("f47686b2-aa5a-11e1-8392-001ec947ccaf")
    private GProperties decodeProperties(final Element domNode) throws IOException {
        GProperties ret = new GProperties();
        for (Element g : DomUtil.getChildren(domNode, "properties")) {
            for (Element p : DomUtil.getChildren(g, "prop")) {
                ret.setProperty(p.getAttribute("name"), parseAttOrTextContent(p,"value"), decodeScope(p, this.defaultScope));
            }
        }
        return ret;
    }

    @objid ("aa7640db-ec75-11e1-912e-001ec947ccaf")
    private ModuleDescriptor decodeModule(final Element domEl) throws IOException {
        ModuleDescriptor md = new ModuleDescriptor();
        
        md.setName(domEl.getAttribute("name"));
        md.setVersion(ProjectDescriptorReader.readVersion(domEl.getAttribute("version")));
        md.setActivated(ProjectDescriptorReader.readBoolean(domEl.getAttribute("activated"), true));
        
        String archiveLoc = domEl.getAttribute("archive");
        try {
            if (archiveLoc != null && ! archiveLoc.isEmpty()) {
                md.setArchiveLocation(new URI(archiveLoc));
            }
        } catch (URISyntaxException e) {
            // Compatibility with 1 version : the 'archive' is a local path.
            try {
                Path p = Paths.get(archiveLoc);
                md.setArchiveLocation(p.toUri());
            } catch (InvalidPathException e2) {
                e.addSuppressed(e2);
                String msg = GProjectData.I18N.getMessage("ProjectDescriptorReader.module.invalidUri", md.getName(), archiveLoc, e.getLocalizedMessage());
                throw new IOException(msg, e);
            }
        }
        
        md.setScope(decodeScope(domEl, md.getArchiveLocation() == null ? null : this.defaultScope));
        md.setAuthDescriptor(decodeAuth(domEl));
        md.setParameters(decodeProperties(domEl));
        return md;
    }

    @objid ("7296fe4e-c4dd-40ec-97dc-427ba5387f00")
    private DefinitionScope decodeScope(final Element domEl, DefinitionScope defaultValue) throws IOException {
        if (this.forcedScope != null) {
            return this.forcedScope;
        }
        
        String ftype = domEl.getAttribute("scope");
        
        if (ProjectDescriptorReader.isEmpty(ftype)) {
            return defaultValue;
        }
        
        try {
            return DefinitionScope.valueOf(ftype);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid fragment scope '" + ftype + "' on fragment '" + domEl.getAttribute("id") + "'", e);
        }
        
    }

    @objid ("47239769-c825-4761-b026-04701dc416cd")
    private AuthDescriptor decodeAuth(final Element domEl) throws IOException {
        AuthDescriptor authDesc = null;
        
        for (Element authNode : DomUtil.getChildren(domEl, "auth")) {
            if (authDesc != null) {
                throw new IOException("More than one <auth> tag on the same node: " + domEl);
            }
        
            authDesc = new AuthDescriptor();
            authDesc.setScope(decodeScope(authNode, this.defaultScope));
        
            final String authScheme = authNode.getAttribute("scheme");
            IAuthData authData = createAuthData(authScheme);
            authDesc.setData(authData);
            if (authData != null) {
                for (Element p : DomUtil.getChildren(authNode, "prop")) {
                    String attVal = parseAttOrTextContent(p,"value");
                    authData.getData().put(p.getAttribute("name"), attVal);
                }
            }
        }
        
        if (authDesc == null) {
            authDesc = new AuthDescriptor(null, DefinitionScope.LOCAL);
        }
        return authDesc;
    }

    /**
     * Create an authentication data from the given scheme id.
     * @return the authentication data
     */
    @objid ("d1767cec-0e77-4445-bfb3-c6ba579105e8")
    private IAuthData createAuthData(String scheme) {
        // if scheme is null, empty or InheritedAuthData.SCHEME_ID auth is inherited from project.
        if (scheme == null) {
            return new InheritedAuthData();
        }
        
        switch (scheme) {
        case AuthDescriptor.AUTH_TYPE_ASK:
            return null;
        
        case "":
        case InheritedAuthData.SCHEME_ID:
            return new InheritedAuthData();
        
        case NoneAuthData.AUTH_NONE_SCHEME_ID:
            return new NoneAuthData();
        
        case UserPasswordAuthData.USERPASS_SCHEME_ID:
            return new UserPasswordAuthData();
        
        case ServerUserPassAuthData.SERVERUSERPASS_SCHEME_ID:
            return new ServerUserPassAuthData();
        
        case SshAuthData.SCHEME_ID:
            return new SshAuthData();
        
        default:
            return new UnknownAuthData(scheme);
        }
        
    }

    @objid ("4364e312-14c6-4774-bdfc-09a1128485ce")
    private InputStream getDecryptedInputStream(final InputStream is, String encryption) throws IOException {
        if (encryption.equals("base64")) {
            return Base64.getDecoder().wrap(is);
        } else if (encryption.isEmpty()) {
            return is;
        } else {
            throw new IOException("Unsupported encryption:" + encryption);
        }
        
    }

    @objid ("1997ee4e-d569-11e1-9f03-001ec947ccaf")
    private static FragmentType decodeFragmentType(final Element domEl) throws IOException {
        String ftype = domEl.getAttribute("type");
        if (ProjectDescriptorReader.isEmpty(ftype)) {
            return null;
        }
        
        try {
            return FragmentType.valueOf(ftype);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid fragment type '" + ftype + "' on fragment '" + domEl.getAttribute("id") + "'", e);
        }
        
    }

    @objid ("0083bdba-f36a-11e1-9173-001ec947ccaf")
    private static Version readVersion(final String s) throws IOException {
        if (ProjectDescriptorReader.isEmpty(s)) {
            return null;
        }
        
        try {
            return new Version(s);
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("844beed5-2ebd-4c0b-8ad4-ec5ecd707623")
    private static boolean readBoolean(final String s, boolean defaultVal) {
        if (ProjectDescriptorReader.isEmpty(s)) {
            return defaultVal;
        }
        return Boolean.parseBoolean(s);
    }

    @objid ("ee35785f-74b2-4a90-b7fb-3dcd6b6a8cc8")
    private void decodeTodo(Element f) throws IOException {
        List<TodoActionDescriptor> actionsList = this.projectDesc.getTodo().getActions();
        for (Element action : DomUtil.getChildren(f)) {
            TodoActionDescriptor desc = null;
            switch (action.getTagName()) {
            case "install_module":
                desc = decodeTodoInstallModule(action);
                break;
            case "update_module":
                desc = decodeTodoUpdateModule(action);
                break;
            case "remove_module":
                desc = decodeTodoRemoveModule(action);
                break;
            default:
                Log.warning("Unknown to-do tag: " + action.getTagName());
            }
        
            if (desc != null) {
                actionsList.add(desc);
            }
        }
        
    }

    @objid ("8e8f168a-3b9d-44a4-bbfc-c2cd7e51d763")
    private TodoActionDescriptor decodeTodoRemoveModule(Element f) {
        final String oldModuleName = f.getAttribute("old_module");
        if (oldModuleName==null || oldModuleName.isEmpty()) {
            // buggy descriptor, ignore
            return null;
        }
        
        RemoveModuleDescriptor d = new RemoveModuleDescriptor();
        d.setModuleName(oldModuleName);
        return d;
    }

    @objid ("68fadfd2-e1d4-4276-9990-43dc4efa3b65")
    private TodoActionDescriptor decodeTodoUpdateModule(Element f) throws IOException {
        UpdateModuleDescriptor d = new UpdateModuleDescriptor();
        d.setOldModuleName(f.getAttribute("old_module"));
        for (Element element : DomUtil.getChildren(f, "new_module")) {
            d.setNewModuleDescriptor(decodeModule(element));
        }
        return null;
    }

    @objid ("4bfb0048-456e-430d-ab3d-ae59810f9502")
    private TodoActionDescriptor decodeTodoInstallModule(Element f) throws IOException {
        InstallModuleDescriptor d = new InstallModuleDescriptor();
        
        for (Element element : DomUtil.getChildren(f, "new_module")) {
            d.setDescriptor(decodeModule(element));
        }
        
        if (d.getModuleDescriptor() != null) {
            return d;
        } else {
            Log.warning("'%s' project descriptor has an empty Todo/InstallModule module.\nTodo=%s",
                    this.projectDesc.getName(), f.getParentNode().getChildNodes());
            return null;
        }
        
    }

    @objid ("f6a73748-eb39-4936-8e56-3a187ec57cb9")
    private static boolean isEmpty(String s) {
        return s == null || s.isEmpty() || s.equals("null");
    }

    @objid ("e2b5ab56-3959-4156-a7e1-216e5e8e011f")
    private ResourceDescriptor decodeResource(final Element domEl) throws IOException {
        ResourceDescriptor rd = new ResourceDescriptor();
        rd.setId(domEl.getAttribute("id"));
        rd.setTargetLocation(domEl.getAttribute("location"));
        String strTime = domEl.getAttribute("timestamp");
        try {
            rd.setTimestamp(Long.parseLong(strTime));
        } catch (NumberFormatException e) {
            throw new IOException(GProjectData.I18N.getMessage("ProjectDescriptorReader.resource.invalidTimestamp", rd.getId(), strTime, e.getMessage()));
        }
        return rd;
    }

    /**
     * Parse a property value style Element that may be serialized either as a DOM attribute or TEXT node.
     * <p>
     * Return the DOM attribute value if defined, the TEXT content in other case.
     * @param domNode the DOM element
     * @param attName the attribute name to look for
     * @return the content of the attribute or the content of the Text node.
     * @since 3.8.1 NG
     */
    @objid ("e5223d85-3206-49ce-836b-af72039976d1")
    private String parseAttOrTextContent(final Element domNode, String attName) {
        String v = domNode.getAttribute(attName);
        if (v.isEmpty())
            v = domNode.getTextContent();
        return v;
    }

}
