/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.editors.richnote.management;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.modelio.app.core.events.ModelioEventTopics;
import org.modelio.editors.richnote.plugin.EditorsRichNote;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Artifact;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * A manager for the extern editors.
 * <p>
 * This class is responsible for handling the activation request on an artifact, by opening a system editor on the file he references.
 * </p>
 */
@objid ("6af83b63-1f07-4b49-8516-5609f4bc11b8")
public class ExternEditorsManager {
    @objid ("21b54b8c-7c3a-4199-91d5-a46618c2b1f0")
    private static final String DIRECTORY = "directory";

    @objid ("335146a5-0917-4a89-b72e-bb3e3aadadb5")
    private static final String FILE = "file";

    @objid ("4e16b11e-2f81-4e82-b51c-3988d099f749")
    private static final String URL = "url";

    @objid ("193efc6c-fee0-4e2d-9938-d8ba6d4f50fd")
    private static final String MODULE_NAME = "ModelerModule";

    @objid ("1d9f77da-c468-4f76-8bf2-ae93acf97166")
    private static final String MAIL = "mail";

    /**
     * Used to keep the singleton in memory.
     */
    @objid ("8b35b0e4-5b5c-4091-abb7-c45750444bfe")
    private static ExternEditorsManager instance;

    @objid ("2ae09786-8c0d-46fb-b001-91d365fc09c1")
    @Inject
    @Optional
    void onEditElement(@UIEventTopic (ModelioEventTopics.EDIT_ELEMENT) final MObject target) {
        if (target instanceof Artifact) {
            Artifact artifact = (Artifact) target;
        
            try {
                if (artifact.isStereotyped(ExternEditorsManager.MODULE_NAME, ExternEditorsManager.FILE) || artifact.isStereotyped(ExternEditorsManager.MODULE_NAME, ExternEditorsManager.DIRECTORY)) {
                    Path filePath = computePath(artifact);
                    if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                        openFile(filePath);
                    }
                }
        
                if (artifact.isStereotyped(ExternEditorsManager.MODULE_NAME, ExternEditorsManager.URL)) {
                    URI fileUri = computeUri(artifact);
                    if (fileUri != null) {
                        browse(fileUri);
                    }
                }
        
                if (artifact.isStereotyped(ExternEditorsManager.MODULE_NAME, ExternEditorsManager.MAIL)) {
                    URI mailToUri = computeMailToUri(artifact);
                    if (mailToUri != null) {
                        mail(mailToUri);
                    }
                }
            } catch (IOException | URISyntaxException | IllegalArgumentException e) {
                EditorsRichNote.LOG.error("Unable to open editor for " + artifact);
            }
        }
    }

    @objid ("96f2c025-0c45-4b61-a047-648e0488539f")
    private void mail(URI mailToUri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.MAIL)) {
                desktop.mail(mailToUri);
            }
        }
    }

    @objid ("c51713d2-b209-488b-a2b5-601cc33583e4")
    private URI computeMailToUri(Artifact artifact) throws URISyntaxException {
        String mailAdress = artifact.getFileName();
        if (mailAdress.contains("@")) {
            return new URI("mailto:" + mailAdress);
        }
        return null;
    }

    @objid ("a4f28ee6-557a-4fdb-96ba-65c06d2baef7")
    private void openFile(Path filePath) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(filePath.toFile());
            }
        }
    }

    @objid ("1617303f-bce0-4464-8257-fd12bd678121")
    private void browse(URI fileUri) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(fileUri);
            }
        }
    }

    @objid ("d2a2673b-2028-4e11-b65f-dee0bdae3aa7")
    public ExternEditorsManager() {
        ExternEditorsManager.instance = this;
    }

    @objid ("7f8d89ef-18d9-4598-bcf8-83a4f8b27090")
    private Path computePath(Artifact fileArtifact) {
        List<String> ownerPaths = new ArrayList<>();
        ModelTree owner = fileArtifact.getOwner();
        while (owner instanceof Artifact && owner.isStereotyped(ExternEditorsManager.MODULE_NAME, ExternEditorsManager.DIRECTORY)) {
            String fileName = ((Artifact) owner).getFileName();
            ownerPaths.add(fileName);
            owner = owner.getOwner();
        }
        String fileName = fileArtifact.getFileName();
        ownerPaths.add(fileName);
        
        String first = ownerPaths.remove(0);
        return Paths.get(first, ownerPaths.toArray(new String[0]));
    }

    @objid ("758f1303-c60a-442a-9719-904a58f243c5")
    private URI computeUri(Artifact urlArtifact) throws URISyntaxException {
        String fileName = urlArtifact.getFileName();
        return fileName.isEmpty() ? null : new URI(fileName);
    }

}
