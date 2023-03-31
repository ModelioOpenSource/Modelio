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
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import net.sf.practicalxml.DomUtil;
import net.sf.practicalxml.ParseUtil;
import net.sf.practicalxml.XmlException;
import org.modelio.gproject.data.plugin.GProjectData;
import org.modelio.gproject.data.project.GProjectPartDescriptor.GProjectPartType;
import org.modelio.gproject.data.project.auth.AuthDescriptor;
import org.modelio.gproject.data.project.auth.InheritedAuthData;
import org.modelio.gproject.data.project.auth.UnknownAuthData;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.SshAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vbasic.version.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/**
 * Read a project descriptor from an XML file.
 */
@objid ("93e500f1-e5be-4300-a767-7d24d9b30558")
public class GProjectDescriptorReader {
    @objid ("c0e41dc8-c722-4e45-8ebf-c00fcec14af3")
    private DefinitionScope forcedScope;

    /**
     * Default scope if no scope specified in file nor forces scope.
     */
    @objid ("6f4928c1-d428-4452-a438-66a1a3ab914e")
    private DefinitionScope defaultScope = null;

    @objid ("552cecad-c741-4120-859a-ebc3f14a479d")
    private Path localProjectPath;

    @objid ("72a67aba-a73c-4334-ba3f-2ec0b195435b")
    private GProjectDescriptor projectDesc;

    /**
     * C'tor.
     */
    @objid ("982fee4c-5ced-427c-a505-ee4864d96a47")
    public  GProjectDescriptorReader() {
        
    }

    /**
     * Read a project descriptor from an XML input source.
     * @param is the XML input source.
     * @param scope the definition scope of the file. If non <code>null</code> all elements will be assumed of the given scope. If <code>null</code>, the scope will be read from the file.
     * @return the read descriptor.
     * @throws IOException in case of failure
     */
    @objid ("620973df-36c8-4b20-816b-68c3269b7088")
    public GProjectDescriptor read(final InputSource is, DefinitionScope scope) throws IOException {
        this.projectDesc = new GProjectDescriptor();
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
     * Looks for an {@link ProjectDescriptorWriter#CONF_ENCRYPT_FILE} file in the same directory to determine encryption of the file.
     * @param confFile The project.conf file path <code>null</code> means the project is in the conf file directory.
     * @param scope the definition scope of the file. If non <code>null</code> all elements will be assumed of the given scope. If <code>null</code>, the scope will be read from the file.
     * @return the read descriptor.
     * @throws IOException in case of failure
     */
    @objid ("e9334d2a-975e-4bc9-ba3a-e9cc18039638")
    public GProjectDescriptor read(final Path confFile, DefinitionScope scope) throws IOException {
        this.localProjectPath = confFile.getParent();
        
        // Look for encryption file
        String encryption = "";
        Path encryptionFile = this.localProjectPath.resolve(GProjectDescriptorWriter.CONF_ENCRYPT_FILE);
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
     * Set the scope to set to read descriptor elements if no scope is specified either in the source nor in the call to {@link #read(InputSource, DefinitionScope)}.
     * @param defaultScope the default scope.
     * @return <code>this</code> for convenience.
     */
    @objid ("a2824ba6-6e16-4ba5-935f-0072b4f75d16")
    public GProjectDescriptorReader setDefaultScope(DefinitionScope defaultScope) {
        this.defaultScope = defaultScope;
        return this;
    }

    /**
     * Convert the project descriptor DOM tree to the current version.
     * @see GProjectDescriptor#serialVersionUID
     * @param p the project descriptor DOM element
     * @param lversion the read format version
     * @throws IOException in case of failure
     */
    @objid ("cd2d8d3b-8914-4c7b-be21-898f1b31b77b")
    private void convertProject(final Element p, final long lversion) throws IOException {
        if (lversion > GProjectDescriptor.serialVersionUID) {
            throw new IOException(GProjectData.I18N.getMessage("GProjectDescriptorReader.format.newer", lversion, GProjectDescriptor.serialVersionUID));
        } else if (lversion == 1) {
            p.setAttribute("type", "LOCAL");
        } else if (lversion == 2) {
            // p.setAttribute("modelioVersion", "???");
            // Modelio <= 3.4.0
        } else if (lversion <= GProjectDescriptor.serialVersionUID) {
            // compatible: nothing to do here
        } else {
            throw new IOException(GProjectData.I18N.getMessage("GProjectDescriptorReader.format.unsupported", lversion, GProjectDescriptor.serialVersionUID));
        }
        
    }

    @objid ("299756e6-5088-4481-be4a-ed472216972c")
    private void decodeProject(final Element projectElt) throws IOException {
        // Check version
        String version = projectElt.getAttribute("version");
        long lversion = Long.parseLong(version);
        if (lversion != GProjectDescriptor.serialVersionUID) {
            convertProject(projectElt, lversion);
        }
        
        // Read name
        this.projectDesc.setName(projectElt.getAttribute("name"));
        
        // Read project type
        this.projectDesc.setType(projectElt.getAttribute("type"));
        
        // Read project path if specified
        String projPath = projectElt.getAttribute("path");
        if (!GProjectDescriptorReader.isEmpty(projPath)) {
            this.projectDesc.setPath(Paths.get(projPath));
        } else {
            this.projectDesc.setPath(this.localProjectPath);
        }
        
        // Read project remote path if specified
        String remotePath = projectElt.getAttribute("remote");
        if (!GProjectDescriptorReader.isEmpty(remotePath)) {
            this.projectDesc.setRemoteLocation(remotePath);
        }
        
        // since 3.4.1 : read Modelio version if specified
        String sv = projectElt.getAttribute("modelioVersion");
        if (!GProjectDescriptorReader.isEmpty(sv)) {
            this.projectDesc.setModelioVersion(new Version(sv));
        }
        
        // Since 3.7 : read project space version
        String sProjSpaceVersion = projectElt.getAttribute("projectSpaceVersion");
        if (!GProjectDescriptorReader.isEmpty(sProjSpaceVersion)) {
            this.projectDesc.setProjectSpaceVersion(Long.parseLong(sProjSpaceVersion));
        } else {
            this.projectDesc.setProjectSpaceVersion(0);
        }
        
        // Read parts: model fragments, modules, features, ramcs, resources
        for (Element f : DomUtil.getChildren(projectElt, "fragment")) {
            this.projectDesc.getPartDescriptors().add(decodeFragment(f));
        }
        
        // Read modules
        for (Element f : DomUtil.getChildren(projectElt, "module")) {
            this.projectDesc.getPartDescriptors().add(decodeModule(f));
        }
        
        // Read features
        for (Element f : DomUtil.getChildren(projectElt, "feature")) {
            this.projectDesc.getPartDescriptors().add(decodeFeature(f));
        }
        
        // Read resources
        for (Element cat : DomUtil.getChildren(projectElt, "resources")) {
            for (Element f : DomUtil.getChildren(cat, "resource")) {
                this.projectDesc.getPartDescriptors().add(decodeResource(f));
            }
        }
        
        // Read project authentication and general properties
        this.projectDesc.setAuthDescriptor(decodeAuth(projectElt));
        this.projectDesc.setProperties(decodeProperties(projectElt));
        
    }

    @objid ("d60d1374-4cc2-41fc-998d-9f80b4664842")
    private GProjectPartDescriptor decodeFragment(final Element domEl) throws IOException {
        String id = domEl.getAttribute("id");
        GProjectPartType type = GProjectDescriptorReader.decodeFragmentType(domEl);
        URI uri = null;
        if (!GProjectDescriptorReader.isEmpty(domEl.getAttribute("uri"))) {
            try {
                uri = new URI(domEl.getAttribute("uri"));
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }
        }
        
        DefinitionScope scope = decodeScope(domEl, uri == null ? null : this.defaultScope);
        Version version = GProjectDescriptorReader.readVersion(domEl.getAttribute("version"));
        
        GProjectPartDescriptor fd = new GProjectPartDescriptor(type, id, version, scope);
        fd.setProperties(decodeProperties(domEl));
        fd.setLocation(uri);
        fd.setAuth(decodeAuth(domEl));
        fd.setLabel(domEl.getAttribute("label"));
        return fd;
    }

    @objid ("8a5be16f-6422-4008-b27a-7e0efce343c9")
    private GProjectPartDescriptor decodeModule(final Element domEl) throws IOException {
        String id = domEl.getAttribute("name");
        URI archiveUri = null;
        
        String archiveLoc = domEl.getAttribute("uri");
        
        if (archiveLoc == null || archiveLoc.isEmpty())
            // Compat previous format
            archiveLoc = domEl.getAttribute("archive");
        
        try {
            if (archiveLoc != null && !archiveLoc.isEmpty()) {
                archiveUri = new URI(archiveLoc);
            }
        } catch (URISyntaxException e) {
            // Compatibility with 1 version : the 'archive' is a local path.
            try {
                Path p = Paths.get(archiveLoc);
                archiveUri = p.toUri();
            } catch (InvalidPathException e2) {
                e.addSuppressed(e2);
                String msg = GProjectData.I18N.getMessage("GProjectDescriptorReader.module.invalidUri", id, archiveLoc, e.getLocalizedMessage());
                throw new IOException(msg, e);
            }
        }
        
        DefinitionScope scope = decodeScope(domEl, archiveUri == null ? null : this.defaultScope);
        
        GProjectPartDescriptor md = new GProjectPartDescriptor(
                GProjectPartType.MODULE,
                id,
                GProjectDescriptorReader.readVersion(domEl.getAttribute("version")),
                scope);
        
        md.setLocation(archiveUri);
        
        // For module 'mountOnOpen' means 'activated'
        md.setLabel(domEl.getAttribute("label"));
        
        md.setAuth(decodeAuth(domEl));
        md.setProperties(decodeProperties(domEl));
        return md;
    }

    @objid ("78054ee2-97e6-480e-9918-d50e49047f59")
    private GProjectPartDescriptor decodeFeature(final Element domEl) throws IOException {
        GProjectPartDescriptor fd = new GProjectPartDescriptor(
                GProjectPartType.FEATURE,
                domEl.getAttribute("id"),
                GProjectDescriptorReader.readVersion(domEl.getAttribute("version")),
                decodeScope(domEl, this.defaultScope));
        
        fd.setProperties(decodeProperties(domEl));
        return fd;
    }

    /**
     * Reads the &lt;properties> tag and returns its content.
     * @param domNode the &lt;properties> DOM element.
     * @return the read properties
     * @throws IOException in case of parse error
     */
    @objid ("c8fff06f-434c-41a7-a836-09c420c77b39")
    private GProperties decodeProperties(final Element domNode) throws IOException {
        GProperties ret = new GProperties();
        for (Element g : DomUtil.getChildren(domNode, "properties")) {
            for (Element p : DomUtil.getChildren(g, "prop")) {
                ret.setProperty(p.getAttribute("name"), parseAttOrTextContent(p, "value"), decodeScope(p, this.defaultScope));
            }
        }
        return ret;
    }

    @objid ("de3be1cd-3fec-44ca-951e-dc919fdef7ad")
    private DefinitionScope decodeScope(final Element domEl, DefinitionScope defaultValue) throws IOException {
        if (this.forcedScope != null) {
            return this.forcedScope;
        }
        
        String ftype = domEl.getAttribute("scope");
        
        if (GProjectDescriptorReader.isEmpty(ftype)) {
            return defaultValue;
        }
        
        try {
            return DefinitionScope.valueOf(ftype);
        } catch (IllegalArgumentException e) {
            throw new IOException("Invalid fragment scope '" + ftype + "' on fragment '" + domEl.getAttribute("id") + "'", e);
        }
        
    }

    @objid ("d751de75-d0a8-4373-b4e1-8b87b2fb8252")
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
                    String attVal = parseAttOrTextContent(p, "value");
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
    @objid ("a769c92b-9c5d-4dfe-916c-3e0e3a9e7f53")
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
        
        case SshAuthData.SCHEME_ID:
            return new SshAuthData();
        
        default:
            return new UnknownAuthData(scheme);
        }
        
    }

    @objid ("f8015cb1-6f06-43cc-9b72-37a679eb739c")
    private InputStream getDecryptedInputStream(final InputStream is, String encryption) throws IOException {
        if (encryption.equals("base64")) {
            return Base64.getDecoder().wrap(is);
        } else if (encryption.isEmpty()) {
            return is;
        } else {
            throw new IOException("Unsupported encryption:" + encryption);
        }
        
    }

    @objid ("b759e29f-adf6-44b6-86fb-5d4352558bb9")
    private static GProjectPartType decodeFragmentType(final Element domEl) throws IOException {
        String ftype = domEl.getAttribute("type");
        if (GProjectDescriptorReader.isEmpty(ftype)) {
            return null;
        }
        try {
            return GProjectPartType.valueOf(ftype);
        } catch (IllegalArgumentException e) {
            // Compatibility
            switch (ftype) {
            case "EXML":
                return GProjectPartType.EXMLFRAGMENT;
            case "EXML_URL":
                return GProjectPartType.HTTPFRAGMENT;
            case "MDA":
            case "RAMC":
                return GProjectPartType.RAMC;
            case "EXML_SVN":
                return GProjectPartType.SVNFRAGMENT;
            default:
                throw new IOException("Invalid fragment type '" + ftype + "' on fragment '" + domEl.getAttribute("id") + "'", e);
            }
        }
        
    }

    @objid ("ca588200-535a-42d1-ac00-2eed92fa56b2")
    private static Version readVersion(final String s) throws IOException {
        if (GProjectDescriptorReader.isEmpty(s)) {
            return null;
        }
        
        try {
            return new Version(s);
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
        
    }

    @objid ("9d38a9c9-79bf-4557-9f77-e06b0d60bf59")
    private static boolean readBoolean(final String s, boolean defaultVal) {
        if (GProjectDescriptorReader.isEmpty(s)) {
            return defaultVal;
        }
        return Boolean.parseBoolean(s);
    }

    @objid ("65f355c8-6568-4d38-a976-e93a62eb666c")
    private static boolean isEmpty(String s) {
        return s == null || s.isEmpty() || s.equals("null");
    }

    @objid ("ef8f5fc8-d33e-47fc-850d-f82ee43ede7a")
    private GProjectPartDescriptor decodeResource(final Element domEl) throws IOException {
        GProjectPartDescriptor rd = new GProjectPartDescriptor(GProjectPartType.RESOURCE, domEl.getAttribute("id"), null, DefinitionScope.SHARED);
        rd.setLabel(domEl.getAttribute("label"));
        
        if (!GProjectDescriptorReader.isEmpty(domEl.getAttribute("location"))) {
            // Migration case
            try {
                rd.setLocation(new URI(domEl.getAttribute("location").replace(" ", "%20")));
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }
        }
        
        if (!GProjectDescriptorReader.isEmpty(domEl.getAttribute("timestamp"))) {
            rd.getProperties().setProperty("timestamp", domEl.getAttribute("timestamp"), DefinitionScope.SHARED);
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
    @objid ("d45615dd-2897-4777-bfe7-274f9e4ee7cd")
    private String parseAttOrTextContent(final Element domNode, String attName) {
        String v = domNode.getAttribute(attName);
        if (v.isEmpty())
            v = domNode.getTextContent();
        return v;
    }

}
