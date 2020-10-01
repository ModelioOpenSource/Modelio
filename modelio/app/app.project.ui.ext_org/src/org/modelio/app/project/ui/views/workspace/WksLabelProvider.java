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

package org.modelio.app.project.ui.views.workspace;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.app.project.ui.plugin.AppProjectUi;
import org.modelio.app.project.ui.plugin.AppProjectUiExt;
import org.modelio.gproject.data.project.ILockInfo;
import org.modelio.gproject.data.project.ProjectDescriptor;
import org.modelio.gproject.data.project.ProjectFileStructure;
import org.modelio.gproject.gproject.GProject;
import org.modelio.platform.project.services.IProjectService;
import org.modelio.platform.ui.UIImages;

/**
 * Workspace tree viewer label provider.
 */
@objid ("a43d5b74-2739-4fd5-b3fe-ead65546a91c")
public class WksLabelProvider extends StyledCellLabelProvider {
    /**
     * Project property containing the project icon path.
     * <p>
     * The icon path must be either:
     * <li>a file path relative to the 'data' project directory.
     * <li>an URL
     */
    @objid ("ed247fdc-1180-49ac-a2d1-6342eb378436")
    private static final String INFO_PROJECT_ICON_NAME = "info.projetIconName";

    @objid ("5d736ce5-337e-477d-b38f-7ae533c442bf")
    private final IProjectService projectService;

    @objid ("1c819bce-0053-4728-82b2-c799eb1bd368")
    private static Image defaultOpenLocalProjectIcon = null;

    @objid ("1d548501-5f44-42be-a949-7dd6dfdc2f70")
    private static Image defaultClosedLocalProjectIcon = null;

    @objid ("2a665053-c3b8-49fb-93ca-8738f0c5c2e9")
    private Font selectedFont;

    @objid ("05656466-e63b-4f3b-a8dc-7f9989c8081a")
    private final Font normalFont;

    @objid ("f8e0aaaa-5be0-49f9-9cdb-2096c87a383f")
    private static Image defaultOpenServerProjectIcon = null;

    @objid ("26be1e35-b9fe-4464-b7a2-459a9361a293")
    private static Image defaultClosedServerProjectIcon = null;

    @objid ("b6c27c06-85c8-48ea-829d-f0a14c7f342b")
    private static Image defaultOpenConstellationProjectIcon = null;

    @objid ("9e952b86-6c94-467e-ad8e-121eaac5ed14")
    private static Image defaultClosedConstellationProjectIcon = null;

    @objid ("f193bc66-7acf-4577-bcd9-3b935d769b79")
    private ImageRegistry icons = new ImageRegistry();

    @objid ("8200b109-3c48-4af5-8720-150a5ec58af5")
    private static Image lockDecoration = null;

    /**
     * Constructor.
     * 
     * @param projectService the project service
     * @param font the font to use.
     */
    @objid ("1f37646e-9e61-4093-a9fc-68b1cae470db")
    public WksLabelProvider(IProjectService projectService, final Font font) {
        FontData[] selectedFontData = WksLabelProvider.getModifiedFontData(font.getFontData(), SWT.BOLD);
        this.selectedFont = new Font(Display.getCurrent(), selectedFontData);
        this.normalFont = font;
        
        if (WksLabelProvider.defaultOpenLocalProjectIcon == null) {
            WksLabelProvider.defaultOpenLocalProjectIcon = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectUi.PLUGIN_ID,
                    "icons/openproject.png").createImage();
        }
        if (WksLabelProvider.defaultClosedLocalProjectIcon == null) {
            WksLabelProvider.defaultClosedLocalProjectIcon = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectUi.PLUGIN_ID,
                    "icons/closedproject.png").createImage();
        }
        
        if (WksLabelProvider.defaultOpenServerProjectIcon == null) {
            WksLabelProvider.defaultOpenServerProjectIcon = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectUi.PLUGIN_ID,
                    "icons/openserverproject.png").createImage();
        }
        if (WksLabelProvider.defaultClosedServerProjectIcon == null) {
            WksLabelProvider.defaultClosedServerProjectIcon = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectUi.PLUGIN_ID,
                    "icons/closedserverproject.png").createImage();
        }
        
        if (WksLabelProvider.lockDecoration == null) {
            WksLabelProvider.lockDecoration = AbstractUIPlugin.imageDescriptorFromPlugin(AppProjectUi.PLUGIN_ID,
                    "images/lock_indicator11x13.png").createImage();
        }
        
        this.projectService = projectService;
    }

    @objid ("4998475a-7209-4ebd-933b-d8ad4c0990ff")
    @Override
    public void update(final ViewerCell cell) {
        Object obj = cell.getElement();
        
        if (obj instanceof ProjectDescriptor) {
            ProjectDescriptor project = (ProjectDescriptor) obj;
        
            if (isCurrentlyOpenedProject(project)) {
                updateOpenedProject(cell, project);
            } else {
                updateClosedProject(cell, project);
            }
        
        } else {
            cell.setText(obj.toString());
        }
    }

    @objid ("21edb116-b51b-4ddc-9f6b-97e7f4730016")
    private static FontData[] getModifiedFontData(final FontData[] originalData, final int additionalStyle) {
        FontData[] styleData = new FontData[originalData.length];
        for (int i = 0; i < styleData.length; i++) {
            FontData base = originalData[i];
            styleData[i] = new FontData(base.getName(), base.getHeight(), base.getStyle() | additionalStyle);
        }
        return styleData;
    }

    @objid ("a5280d20-2eaf-48cf-9a3d-1a72c3748fc9")
    private void updateOpenedProject(final ViewerCell cell, final ProjectDescriptor project) {
        cell.setFont(this.selectedFont);
        cell.setText(getProjectLabel(project));
        Image icon = getProjectIcon(project);
        if (icon == null) {
            switch (ProjectType.get(project)) {
            case LOCAL:
                icon = WksLabelProvider.defaultOpenLocalProjectIcon;
                break;
            default:
            case SERVER:
                icon = WksLabelProvider.defaultOpenServerProjectIcon;
                break;
            case CONSTELLATION:
                icon = WksLabelProvider.defaultOpenConstellationProjectIcon;
                break;
            }
        }
        cell.setImage(icon);
    }

    @objid ("e56fb3bb-8c37-407a-acef-68957789f598")
    private void updateClosedProject(final ViewerCell cell, final ProjectDescriptor project) {
        cell.setFont(this.normalFont);
        cell.setText(getProjectLabel(project));
        Image icon = getProjectIcon(project);
        if (icon == null) {
            switch (ProjectType.get(project)) {
            case LOCAL:
                icon = WksLabelProvider.defaultClosedLocalProjectIcon;
                break;
            default:
            case SERVER:
                icon = WksLabelProvider.defaultClosedServerProjectIcon;
                break;
            case CONSTELLATION:
                icon = WksLabelProvider.defaultClosedConstellationProjectIcon;
                break;
            }
        }
        cell.setImage(icon);
    }

    @objid ("1909ecd6-c2de-4419-abf2-30139293df17")
    @Override
    public void dispose() {
        super.dispose();
        
        this.selectedFont.dispose();
        this.selectedFont = null;
        
        this.icons.dispose();
        this.icons = null;
    }

    @objid ("f2fb3f5f-71e3-4e4d-bf1b-8057d9820aa1")
    private boolean isCurrentlyOpenedProject(ProjectDescriptor projectDescriptor) {
        final GProject currentlyOpenedProject = this.projectService.getOpenedProject();
        return currentlyOpenedProject != null && currentlyOpenedProject.getProjectFileStructure().getProjectPath().equals(projectDescriptor.getProjectFileStructure().getProjectPath());
    }

    @objid ("c8d6ac2d-739c-42df-97c6-2b68b4d5c5bb")
    private Image getProjectIcon(final ProjectDescriptor project) {
        ProjectFileStructure projectFileStructure = project.getProjectFileStructure();
        Image projectIcon = this.icons.get(projectFileStructure.getProjectPath().toString());
        if (projectIcon == null) {
            String iconName = project.getProperties().getValue(WksLabelProvider.INFO_PROJECT_ICON_NAME);
            if (iconName == null) {
                return null;
            }
        
            Path iconPath = projectFileStructure.getProjectDataPath().resolve(iconName);
            projectIcon = createUserProjectIcon(iconPath);
        
            if (projectIcon == null) {
                projectIcon = createImageFromUrl(iconName);
            }
        
            if (projectIcon != null) {
                this.icons.put(projectFileStructure.getProjectPath().toString(), projectIcon);
            }
        }
        return projectIcon;
    }

    @objid ("d031dce1-ccb0-434d-94db-3e8752d6468f")
    private Image createUserProjectIcon(Path path) {
        Image projectIcon = null;
        if (Files.exists(path)) {
            Image originalImage = null;
            try {
                originalImage = new Image(null, path.toString());
                ImageData imagePlaceholderData = UIImages.PLACEHOLDER.getImageData();
                projectIcon = new Image(null, originalImage.getImageData().scaledTo(imagePlaceholderData.width, imagePlaceholderData.height));
            } finally {
                if (originalImage != null) {
                    originalImage.dispose();
                }
            }
        }
        return projectIcon;
    }

    @objid ("2c38442c-9528-4a2a-8c5d-faa59e1a1f44")
    private String getProjectLabel(ProjectDescriptor project) {
        String text;
        Path p = project.getProjectFileStructure().getProjectPath();
        if (p.getNameCount() > 0) {
            text = p.getName(p.getNameCount() - 1).toString();
        } else {
            text = project.getName();
        }
        return text;
    }

    @objid ("8dad45ab-7496-4e83-8283-ebf050605919")
    private Image createImageFromUrl(String path) {
        Image originalImage = null;
        try {
            URL url = new URL(path);
            originalImage = ImageDescriptor.createFromURL(url).createImage(false, null);
        
            ImageData imagePlaceholderData = UIImages.PLACEHOLDER.getImageData();
            return new Image(null, originalImage.getImageData().scaledTo(imagePlaceholderData.width, imagePlaceholderData.height));
        } catch (@SuppressWarnings ("unused") MalformedURLException e) {
            // ignore exception and return null
            return null;
        } finally {
            if (originalImage != null) {
                originalImage.dispose();
            }
        }
    }

    @objid ("e610a03c-8d1d-4080-a9c8-020e45780f27")
    @Override
    protected void paint(Event event, Object element) {
        super.paint(event, element);
        
        // Add lock icon on locked projects
        if (element instanceof ProjectDescriptor) {
            ProjectDescriptor desc = (ProjectDescriptor) element;
        
            ILockInfo lockInfo = desc.getLockInfo();
            if (lockInfo != null) {
                if (!lockInfo.isSelf()) {
                    int offset = 16 - WksLabelProvider.lockDecoration.getBounds().width - 1;
                    event.gc.drawImage(WksLabelProvider.lockDecoration, event.x + offset, event.y + 3);
                }
            }
        }
    }

    @objid ("e51fc0fd-57bd-4e70-80b3-0b8d05c572c3")
    @Override
    public String getToolTipText(Object element) {
        if (element instanceof ProjectDescriptor) {
            ProjectDescriptor project = (ProjectDescriptor) element;
        
            ILockInfo lockInfo = project.getLockInfo();
            if (!isCurrentlyOpenedProject(project) && lockInfo != null && !lockInfo.isSelf()) {
                return AppProjectUiExt.I18N.getMessage("WksLabelProvider.lockedTooltip",
                        project.getName(),
                        lockInfo.getOwner(),
                        lockInfo.getHostName(),
                        lockInfo.getDate());
            }
        }
        
        // standard behavior
        return super.getToolTipText(element);
    }

    @objid ("7a6f533f-f49f-47d4-9786-3c6367241c74")
    private enum ProjectType {
        LOCAL,
        SERVER,
        CONSTELLATION;

        @objid ("f8761708-781a-4ddc-92b2-8015fc30d851")
        private static final String PROJECT_TYPE_LOCAL = "LOCAL";

        @objid ("d90383c2-1544-4c6a-ad10-5c8e4ab5b8ff")
        public static ProjectType get(final ProjectDescriptor project) {
            if (project.getType().equals(ProjectType.PROJECT_TYPE_LOCAL)) {
                return LOCAL;
            } else if (project.getRemoteLocation().startsWith("constellation:")) {
                return CONSTELLATION;
            } else {
                return SERVER;
            }
        }

    }

}
