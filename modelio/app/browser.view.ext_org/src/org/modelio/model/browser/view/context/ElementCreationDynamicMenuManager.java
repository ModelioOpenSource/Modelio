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

package org.modelio.model.browser.view.context;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.commands.MCommand;
import org.eclipse.e4.ui.model.application.commands.MCommandsFactory;
import org.eclipse.e4.ui.model.application.commands.MParameter;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuSeparator;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.modelio.app.project.core.services.IProjectService;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.core.ui.swt.images.MetamodelImageService;
import org.modelio.model.browser.view.plugin.ModelBrowserExtOrg;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Populates the creation menu with standard creation items.
 * <p>
 * Run as E4 Model processor from the plugin.xml
 */
@objid ("0053e972-7a19-1006-9c1d-001ec947cd2a")
@Creatable
@Singleton
public class ElementCreationDynamicMenuManager {
    /**
     * Menu identifier in the .e4xmi file.
     */
    @objid ("008b0286-7a2b-1006-9c1d-001ec947cd2a")
    private static final String MENUID = "org.modelio.model.browser.view.menu.createelement";

    @objid ("008a07fa-7a2b-1006-9c1d-001ec947cd2a")
    private static final String CONTRIBUTOR_ID = "platform:/plugin/" + ModelBrowserExtOrg.PLUGIN_ID;

    @objid ("0054108c-7a19-1006-9c1d-001ec947cd2a")
    private Map<String, Set<String>> validCases = new HashMap<>();

    @objid ("00545da8-7a19-1006-9c1d-001ec947cd2a")
    @Inject
    private MApplication application;

    @objid ("0054822e-7a19-1006-9c1d-001ec947cd2a")
    private Map<String, MCommand> commandCache = new HashMap<>();

    @objid ("0054ae34-7a19-1006-9c1d-001ec947cd2a")
    @Inject
    @Named (ElementCreationDynamicMenuManager.MENUID)
    private MMenu menu;

    /**
     * Keep instance to avoid triggering the garbage collector.
     */
    @objid ("2de6b2c9-f0ba-4a4a-8133-937adcd557e6")
    private static ElementCreationDynamicMenuManager INSTANCE;

    /**
     * Service needed to get the active MPart because
     * 
     * @Named(IServiceConstants.ACTIVE_PART) parameter does not work.
     */
    @objid ("ba333369-d960-4b87-a146-8fe7bbc58d14")
    @Inject
    private EPartService partSvc;

    @objid ("98851fcb-9079-4e90-82a2-9589560031be")
    @Inject
    private IProjectService projectService;

    /**
     * Update creation commands to be displayed for the given object.
     */
    @objid ("005440b6-7a19-1006-9c1d-001ec947cd2a")
    private void configure(MMenu menuToConfigure, MObject obj) {
        // The injected menu might not be the same, use this one instead...
        String sourceMetaclass = obj != null ? obj.getMClass().getQualifiedName() : null;
        
        Set<String> validCommands = this.validCases.get(sourceMetaclass);
        if (validCommands == null) {
            for (MMenuElement item : menuToConfigure.getChildren()) {
                item.setVisible(false);
            }
        } else {
            for (MMenuElement item : menuToConfigure.getChildren()) {
                boolean isVisible = validCommands.contains(item.getElementId());
                item.setVisible(isVisible);
            }
        }
    }

    @objid ("0054c720-7a19-1006-9c1d-001ec947cd2a")
    @PostConstruct
    void onInit() {
        if (this.menu != null) {
            Loader loader = new Loader();
        
            Bundle bundle = ModelBrowserExtOrg.getContext().getBundle();
        
            IPath explorerContextualMenu = new Path("/res/create-popups.xml");
            URL url = FileLocator.find(bundle, explorerContextualMenu, null);
            loader.loadXML(url, this);
        
            // free some ressources
            this.commandCache.clear();
            this.commandCache = null;
        } else {
            ModelBrowserExtOrg.LOG.error("Unable to initialize element creation menu");
        }
        ElementCreationDynamicMenuManager.INSTANCE = this;
    }

    @objid ("0054dfe4-7a19-1006-9c1d-001ec947cd2a")
    private MCommand getCommand(String commandId) {
        MCommand command = this.commandCache.get(commandId);
        if (command == null) {
            for (MCommand c : this.application.getCommands()) {
                if (commandId.equals(c.getElementId())) {
                    command = c;
                    this.commandCache.put(commandId, command);
                    break;
                }
            }
        }
        return command;
    }

    @objid ("005507ee-7a19-1006-9c1d-001ec947cd2a")
    private void register(EntryDescriptor entryDescriptor) {
        String sourceMetaclass = entryDescriptor.sourceMetaclass;
        String dependency = entryDescriptor.parameters.getProperty("dependency", "");
        String targetMetaclass = entryDescriptor.parameters.getProperty("metaclass", "");
        String targetStereotype = entryDescriptor.parameters.getProperty("stereotype", "");
        String i18nKey = entryDescriptor.parameters.getProperty("i18nKey", "");
        if (i18nKey.isEmpty()) {
            // Build a key with simple metaclasses instead of qualified metaclasses
            i18nKey = "$" + sourceMetaclass.replaceAll("^[a-zA-Z]+\\.", "") + dependency + targetMetaclass.replaceAll("^[a-zA-Z]+\\.", "") + targetStereotype;
        }
        
        // create a new item
        MHandledMenuItem item = MMenuFactory.INSTANCE.createHandledMenuItem();
        MCommand command = getCommand(entryDescriptor.commandId);
        item.setCommand(command);
        
        item.setElementId(sourceMetaclass + dependency + targetMetaclass + targetStereotype);
        
        item.setLabel(ModelBrowserExtOrg.I18N.getString(i18nKey + ".label"));
        item.setTooltip(ModelBrowserExtOrg.I18N.getString(i18nKey + ".tooltip"));
        
        String baseIcon = ModelBrowserExtOrg.I18N.getString(i18nKey + ".icon");
        if (baseIcon != null) {
            item.setIconURI(MetamodelImageService.getIconCompletePath(baseIcon));
        }
        
        item.setEnabled(true);
        item.setToBeRendered(true);
        item.setVisible(false);
        
        item.setContributorURI(ElementCreationDynamicMenuManager.CONTRIBUTOR_ID);
        
        for (Entry<Object, Object> param : entryDescriptor.parameters.entrySet()) {
            MParameter p = MCommandsFactory.INSTANCE.createParameter();
            p.setName((String) param.getKey());
            p.setValue((String) param.getValue());
            item.getParameters().add(p);
        }
        
        this.menu.getChildren().add(item);
        
        registerValidCase(sourceMetaclass, item.getElementId());
    }

    @objid ("00551be4-7a19-1006-9c1d-001ec947cd2a")
    private void registerValidCase(String sourceMetaclass, String contributionId) {
        if (!this.validCases.containsKey(sourceMetaclass)) {
            this.validCases.put(sourceMetaclass, new HashSet<String>());
        }
        
        this.validCases.get(sourceMetaclass).add(contributionId);
    }

    @objid ("987e9920-f1e4-4675-a169-28e7f6e038ae")
    private void registerSeparator(String sourceMetaclass) {
        // create a new item
        MMenuSeparator item = MMenuFactory.INSTANCE.createMenuSeparator();
        
        // Build a unique ID for the separator...
        item.setElementId(sourceMetaclass + ".separator." + this.menu.getChildren().size());
        
        item.setToBeRendered(true);
        item.setVisible(true);
        
        item.setContributorURI(ElementCreationDynamicMenuManager.CONTRIBUTOR_ID);
        
        this.menu.getChildren().add(item);
        
        registerValidCase(sourceMetaclass, item.getElementId());
    }

    @objid ("eec8b7bf-c568-4e68-8a39-77cd16e80bcb")
    @Optional
    @Inject
    void onUpdate(@Named (IServiceConstants.ACTIVE_SELECTION) final IStructuredSelection selection) {
        MPart part = null;
        try {
            part = this.partSvc.getActivePart();
        } catch (@SuppressWarnings ("unused") IllegalStateException e) {
            // java.lang.IllegalStateException: Application does not have an active window
            // at org.eclipse.e4.ui.internal.workbench.ApplicationPartServiceImpl.getActiveWindowService(ApplicationPartServiceImpl.java:43)
        
            // Ignore this stupid ugly Eclipse 4 exception.
        }
        
        if (part != null) {
            // Do not display anything if the selection contains more than one element
            MObject selectedElement = selection != null && selection.size() == 1 ? SelectionHelper.getFirst(selection, MObject.class) : null;
        
            // Get the dynamic creation menu from the current part, it can't be injected right here...
            for (MMenu browserMenu : part.getMenus()) {
                for (MMenuElement subMenu : browserMenu.getChildren()) {
                    if (subMenu.getElementId().equals(ElementCreationDynamicMenuManager.MENUID)) {
                        configure((MMenu) subMenu, selectedElement);
                    }
                }
            }
        }
    }

    @objid ("d80b6fb5-a893-4b92-82ad-25f74d8ca734")
    private static final class Loader {
        @objid ("a4a7dde9-bae4-45ac-9ab2-f938971c4cda")
        public void loadXML(URL url, ElementCreationDynamicMenuManager elementCreationDynamicMenuManager) {
            try (InputStream xmlStream = url.openStream()) {
                // Create a DocumentBuilderFactory
                final DocumentBuilderFactory dbf = DocumentBuilderFactory
                        .newInstance();
                dbf.setNamespaceAware(true);
                dbf.setXIncludeAware(false);
                // dbf.setSchema(schema);
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, false);
            
                // Create a DocumentBuilder
                final DocumentBuilder db = dbf.newDocumentBuilder();
            
                // Parse
                // db.setErrorHandler(parserAPIUsage);
                final Document xmlDoc = db.parse(xmlStream);
                final Element rootElement = xmlDoc.getDocumentElement();
                parseMenus(rootElement, elementCreationDynamicMenuManager);
                xmlStream.close();
            } catch (final ParserConfigurationException e) {
                e.printStackTrace();
            } catch (final SAXException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        @objid ("f716121b-26fb-433b-aa0e-18967210d93a")
        private void parseMenus(Element rootElement, ElementCreationDynamicMenuManager elementCreationDynamicMenuManager) {
            // Explore nodes under the root node:
            final NodeList childNodes = rootElement.getChildNodes();
            
            final int nNodes = childNodes.getLength();
            for (int i = 0; i < nNodes; i++) {
                final Node node = childNodes.item(i);
                if (node.getNodeName().equals("popup")) {
                    parsePopupNode(node, elementCreationDynamicMenuManager);
                }
            }
        }

        @objid ("a8003476-e80c-43c8-92e7-a0159eb77e89")
        private void parsePopupNode(Node popupNode, ElementCreationDynamicMenuManager elementCreationDynamicMenuManager) {
            // Get the metaclass name
            final NamedNodeMap attributes = popupNode.getAttributes();
            String metaclassName = "";
            String stereotypeName = "";
            
            // Get source metaclass and stereotype
            if (attributes != null) {
                final Node metaclassNode = attributes.getNamedItem("metaclass");
                if (metaclassNode != null) {
                    metaclassName = metaclassNode.getTextContent();
                }
                final Node stereotypeNode = attributes.getNamedItem("stereotype");
                if (stereotypeNode != null) {
                    stereotypeName = stereotypeNode.getTextContent();
                }
            }
            
            // Explore items and menus
            final NodeList menuNodes = popupNode.getChildNodes();
            final int nNodes = menuNodes.getLength();
            for (int i = 0; i < nNodes; i++) {
                final Node node = menuNodes.item(i);
            
                if (node.getNodeName().equals("command")) {
                    EntryDescriptor entryDescriptor = parseCommandNode(node);
                    entryDescriptor.sourceMetaclass = metaclassName;
                    entryDescriptor.sourceStereotype = stereotypeName;
            
                    elementCreationDynamicMenuManager.register(entryDescriptor);
                } else if (node.getNodeName().equals("separator")) {
                    elementCreationDynamicMenuManager.registerSeparator(metaclassName);
                }
            }
        }

        @objid ("99c84045-91af-49b7-ac3a-b6807a0c9f18")
        private EntryDescriptor parseCommandNode(Node itemNode) {
            final EntryDescriptor entryDescriptor = new EntryDescriptor();
            
            final NamedNodeMap attributes = itemNode.getAttributes();
            String id = "";
            
            // get command id
            if (attributes != null) {
                final Node nameNode = attributes.getNamedItem("id");
                if (nameNode != null) {
            
                    id = nameNode.getTextContent();
                }
            }
            entryDescriptor.commandId = id;
            
            // parse command parameters
            final NodeList childNodes = itemNode.getChildNodes();
            final int nNodes = childNodes.getLength();
            for (int i = 0; i < nNodes; i++) {
                final Node node = childNodes.item(i);
                if (node.getNodeName().equals("parameter")) {
                    parseParameterNode(node, entryDescriptor);
                }
            }
            return entryDescriptor;
        }

        @objid ("ee3ae112-069c-4dda-88ef-ce43ea64c421")
        private void parseParameterNode(Node parameterNode, EntryDescriptor entryDescriptor) {
            final NamedNodeMap attributes = parameterNode.getAttributes();
            String name = "";
            String value = "";
            
            if (attributes != null) {
                final Node nameNode = attributes.getNamedItem("name");
                if (nameNode != null) {
                    name = nameNode.getTextContent();
                }
                final Node valueNode = attributes.getNamedItem("value");
                if (valueNode != null) {
                    value = valueNode.getTextContent();
                }
                entryDescriptor.parameters.put(name, value);
            }
        }

    }

    @objid ("bf9c9b8f-6545-4c85-b64e-79eaa6950c73")
    private static final class EntryDescriptor {
        @objid ("4cc6ef1b-bcf7-457e-acbf-2c7706ac039c")
        public String sourceMetaclass;

        @objid ("1efb13ca-7336-4bad-996b-15cfdb1f2ec3")
        public String sourceStereotype;

        @objid ("a8bf2ece-b68a-4c00-b6b6-c35ca33ea916")
        public String commandId;

        @objid ("1ddd259e-a447-46e9-8b91-f5ffc92eed3b")
        public Properties parameters = new Properties();

    }

}
