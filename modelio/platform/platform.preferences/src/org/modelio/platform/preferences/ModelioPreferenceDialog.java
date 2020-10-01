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

package org.modelio.platform.preferences;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.contributions.IContributionFactory;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.modelio.platform.preferences.plugin.Preferences;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.platform.ui.plugin.UI;

/**
 * The GUI (dialog box) of the modelio preferences dialog
 */
@objid ("bba529d2-3bcb-49e7-b10f-f83a3bb55a47")
public class ModelioPreferenceDialog extends PreferenceDialog {
    /**
     * ID of the eclipse contribution point used for preferences pages.
     */
    @objid ("a5a1c970-27b2-4665-9edd-678676a04a4d")
    public static final String PREFS_PAGE_XP = "org.modelio.platform.preferencespages"; // $NON-NLS-1$

    @objid ("d29aa870-ed62-4e6a-a9e9-b64eb6c3a7f4")
    protected static final String ELMT_PAGE = "page"; // $NON-NLS-1$

    @objid ("232e9de8-8c99-4e61-a8f0-153437863c26")
    protected static final String ATTR_ID = "id"; // $NON-NLS-1$

    @objid ("fdfd0bea-be48-4458-b700-2a0ebbd5de97")
    protected static final String ATTR_CATEGORY = "category"; // $NON-NLS-1$

    @objid ("8ca9235b-b677-4427-aef3-64e4d4ef2f0a")
    protected static final String ATTR_CLASS = "class"; // $NON-NLS-1$

    @objid ("16a275c6-bb09-4f90-b670-44d5a1606502")
    protected static final String ATTR_NAME = "name"; // $NON-NLS-1$

    @objid ("07a267fa-9e6b-4e24-9c4a-2898b14b9cce")
    private static final String DLG_IMG_TITLE_BANNER = "dialog_title_banner_image"; // $NON-NLS-1$

    @objid ("baa43f73-ff26-4c28-8241-80b7ca1cc153")
    private int _messageLabelHeight;

    @objid ("d51d9563-187d-4636-ae7a-601c42905358")
    private boolean _titleImageLargest;

    @objid ("3a1ef249-9673-4d8d-bc9a-ec0ee252be49")
    private String _message;

    @objid ("5002f13d-b176-46f5-9c15-2ce1142cebcf")
    private final List<IPreferenceNode> nodes;

    @objid ("85b53bb3-b54b-4a5b-9021-e8b73927d532")
    protected static final String ATTR_ICON = "icon"; // $NON-NLS-1$

    @objid ("30fca58c-8a46-4298-a97a-d13331fef3ef")
    private Composite _titleArea;

    @objid ("ce4c23ff-5a08-47b9-a23b-3087377093e2")
    protected Color _titleAreaColor;

    @objid ("281066e9-387d-446e-8d9f-fbd07adbb840")
    private RGB _titleAreaRGB;

    @objid ("5b089e44-7e76-4ad1-a613-1e95820913e4")
    private Label _titleRightImageLabel;

    @objid ("c9d14241-7463-404d-a705-9c0eb3316843")
    private Image _titleLeftImage;

    @objid ("935e43e0-7d21-4ac4-88ed-c704b5c56676")
    private Label _titleLeftImageLabel;

    @objid ("8b24c0d6-349f-4ada-b1ce-7c490dc964aa")
    private Image _titleRightImage;

    @objid ("7a3e1e4e-010e-4fc2-95fa-9241bd02e895")
    private Label _titleLabel;

    @objid ("f46792a9-5392-480c-bb67-7e37b1529539")
    private Text _messageLabel;

    @objid ("f8a6a3d0-52f0-4922-bb2e-2b6972f7c0e7")
    private Label _messageImageLabel;

    @objid ("497c9ceb-c05a-4927-9db6-d1cda429f201")
    private Label _leftFillerLabel;

    /**
     * Initialize the preferences dialog.
     * 
     * @param parentShell a shell.
     * @param context the eclipse context.
     */
    @objid ("a692a03a-0d5e-4be5-aa99-36fc0fffa1ed")
    public ModelioPreferenceDialog(final Shell parentShell, final IEclipseContext context) {
        super(parentShell, configurePreferences(context));
        
        this._message = "";
        
        this.nodes = new ArrayList<>();
    }

    @objid ("e9a249e2-079a-4985-ba23-18adc676fd90")
    @Override
    public boolean close() {
        for (final IPreferenceNode node : this.nodes) {
            getPreferenceManager().remove(node);
        }
        return super.close();
    }

    @objid ("7be28e2e-383a-4372-bd74-59be574831ac")
    @Override
    protected Control createDialogArea(final Composite parent) {
        createTitle(parent);
        
        setTitle(Preferences.I18N.getString("ParametersDlg.Title"));
        setDialogMessage(Preferences.I18N.getString("ParametersDlg.Desc"));
        
        parent.addTraverseListener(new TraverseListener() {
            @Override
            public void keyTraversed(final TraverseEvent event) {
                if (event.character == SWT.CR || event.character == SWT.KEYPAD_CR || event.character == SWT.ESC) {
                    event.doit = false;
                }
            }
        });
        return super.createDialogArea(parent);
    }

    @objid ("291c8a82-7e9d-4390-b89c-2e5d3d168015")
    protected void setTitleLeftImage(final Image newTitleImage) {
        this._titleLeftImage = newTitleImage;
        if (this._titleLeftImageLabel != null && !this._titleLeftImageLabel.isDisposed()) {
            this._titleLeftImageLabel.setImage(newTitleImage);
            this._titleLeftImageLabel.setVisible(newTitleImage != null);
        }
    }

    @objid ("41338c48-dbbd-4a05-9db5-aed3b9cc7b9e")
    private void setLayoutsForNormalMessage(final int verticalSpacing, final int horizontalSpacing) {
        final FormData messageLabelData = new FormData();
        messageLabelData.top = new FormAttachment(this._titleLabel, verticalSpacing);
        messageLabelData.right = new FormAttachment(this._titleRightImageLabel);
        messageLabelData.left = new FormAttachment(this._messageImageLabel, horizontalSpacing);
        
        if (!this._titleImageLargest) {
            messageLabelData.bottom = new FormAttachment(this._titleRightImageLabel, 0, SWT.BOTTOM);
        }
        
        this._messageLabel.setLayoutData(messageLabelData);
        
        final FormData imageLabelData = new FormData();
        imageLabelData.top = new FormAttachment(this._titleLabel, verticalSpacing);
        imageLabelData.left = new FormAttachment(this._titleLeftImageLabel);
        this._messageImageLabel.setLayoutData(imageLabelData);
        
        final FormData data = new FormData();
        data.top = new FormAttachment(this._titleLabel, 0, SWT.TOP);
        data.left = new FormAttachment(this._titleLeftImageLabel);
        data.bottom = new FormAttachment(this._messageLabel, 0, SWT.BOTTOM);
        this._leftFillerLabel.setLayoutData(data);
    }

    @objid ("34033251-be5a-463e-9c7b-3fa486ef059b")
    private void determineTitleImageLargest() {
        final int titleY = this._titleLeftImageLabel.computeSize(-1, -1).y;
        final int verticalSpacing = convertVerticalDLUsToPixels(1);
        int labelY = this._titleLabel.computeSize(-1, -1).y;
        labelY += verticalSpacing;
        labelY += this._messageLabelHeight;
        labelY += verticalSpacing;
        this._titleImageLargest = titleY > labelY;
    }

    @objid ("30b98bd5-cab0-4393-b635-b2702b1f80ed")
    private void createTitle(final Composite parent) {
        // image from plugin
        final ImageDescriptor imageDescriptor = UI.getImageDescriptor("images/headerleft110x50.png");
        
        final Image image = imageDescriptor.createImage();
        setTitleLeftImage(image);
        
        this._titleArea = new Composite(parent, SWT.NONE);
        initializeDialogUnits(this._titleArea);
        final GridData titleAreaData = new GridData(SWT.FILL, SWT.TOP, true, false);
        
        this._titleArea.setLayoutData(titleAreaData);
        final FormLayout layout = new FormLayout();
        this._titleArea.setLayout(layout);
        this._titleArea.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(final DisposeEvent e) {
                if (ModelioPreferenceDialog.this._titleAreaColor != null) {
                    ModelioPreferenceDialog.this._titleAreaColor.dispose();
                }
            }
        });
        
        final org.eclipse.swt.widgets.Display display = this._titleArea.getDisplay();
        Color background;
        Color foreground;
        if (this._titleAreaRGB != null) {
            this._titleAreaColor = new Color(display, this._titleAreaRGB);
            background = this._titleAreaColor;
            foreground = null;
        } else {
            background = JFaceColors.getBannerBackground(display);
            foreground = JFaceColors.getBannerForeground(display);
        }
        final int verticalSpacing = convertVerticalDLUsToPixels(1);
        final int horizontalSpacing = convertHorizontalDLUsToPixels(4);
        this._titleArea.setBackground(background);
        
        this._titleRightImageLabel = new Label(this._titleArea, SWT.NONE);
        this._titleRightImageLabel.setBackground(background);
        if (this._titleRightImage != null && !this._titleRightImage.isDisposed()) {
            this._titleRightImageLabel.setImage(this._titleRightImage);
        }
        
        this._titleLeftImageLabel = new Label(this._titleArea, SWT.NONE);
        this._titleLeftImageLabel.setBackground(background);
        if (this._titleLeftImage == null || this._titleLeftImage.isDisposed()) {
            this._titleLeftImageLabel.setImage(JFaceResources.getImage(ModelioPreferenceDialog.DLG_IMG_TITLE_BANNER));
        } else {
            this._titleLeftImageLabel.setImage(this._titleLeftImage);
        }
        
        final FormData rightImageData = new FormData();
        rightImageData.top = new FormAttachment(0, 0);
        rightImageData.right = new FormAttachment(100, 0);
        this._titleRightImageLabel.setLayoutData(rightImageData);
        
        final FormData leftImageData = new FormData();
        leftImageData.top = new FormAttachment(0, 0);
        leftImageData.left = new FormAttachment(0, 0);
        this._titleLeftImageLabel.setLayoutData(leftImageData);
        
        this._titleLabel = new Label(this._titleArea, SWT.NONE);
        JFaceColors.setColors(this._titleLabel, foreground, background);
        this._titleLabel.setFont(JFaceResources.getBannerFont());
        this._titleLabel.setText(" ");
        final FormData titleData = new FormData();
        titleData.left = new FormAttachment(this._titleLeftImageLabel, 5);
        titleData.top = new FormAttachment(0, verticalSpacing);
        titleData.right = new FormAttachment(this._titleRightImageLabel, -5);
        this._titleLabel.setLayoutData(titleData);
        this._messageImageLabel = new Label(this._titleArea, SWT.NONE);
        this._messageImageLabel.setBackground(background);
        this._messageLabel = new Text(this._titleArea, SWT.READ_ONLY | SWT.MULTI);
        JFaceColors.setColors(this._messageLabel, foreground, background);
        this._messageLabel.setFont(JFaceResources.getDialogFont());
        this._messageLabelHeight = this._messageLabel.computeSize(-1, -1).y;
        this._leftFillerLabel = new Label(this._titleArea, SWT.NONE);
        this._leftFillerLabel.setBackground(background);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
        determineTitleImageLargest();
        
        final Label titleBarSeparator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
        final GridData barData = new GridData(768);
        titleBarSeparator.setLayoutData(barData);
    }

    @objid ("d8090ce1-9d85-474f-9c3d-6b5050e32882")
    protected void setTitle(final String newTitle) {
        if (this._titleLabel == null) {
            return;
        }
        String title = newTitle;
        if (title == null) {
            title = "";
        }
        this._titleLabel.setText(title);
    }

    @objid ("778403ee-9dfc-4c1d-ae1b-f35f23ef3be8")
    private void setDialogMessage(final String newMessage) {
        if (this._message.equals(newMessage)) {
            return;
        }
        
        this._message = newMessage;
        
        if (this._message == null) {
            this._message = "";
        }
        
        this._messageLabel.setText(newMessage);
        layoutForNewMessage();
    }

    @objid ("23f098ef-af6e-4ad5-af83-97eab92e773c")
    private void layoutForNewMessage() {
        final int verticalSpacing = convertVerticalDLUsToPixels(1);
        final int horizontalSpacing = convertHorizontalDLUsToPixels(4);
        setLayoutsForNormalMessage(verticalSpacing, horizontalSpacing);
        if (this.dialogArea != null) {
            this._titleArea.layout(true);
        }
    }

    @objid ("70ca0447-23a9-4e48-a547-7763389cb376")
    private static PreferenceManager configurePreferences(final IEclipseContext context) {
        final Set<IPreferenceNode> rootPreferences = new TreeSet<>(new Comparator<IPreferenceNode>() {
            @Override
            public int compare(final IPreferenceNode o1, final IPreferenceNode o2) {
                return o1.getLabelText().compareTo(o2.getLabelText());
            }
        });
        
        final IContributionFactory factory = context.get(IContributionFactory.class);
        
        for (final IConfigurationElement elmt : new ExtensionPointContributionManager(ModelioPreferenceDialog.PREFS_PAGE_XP).getExtensions(ModelioPreferenceDialog.ELMT_PAGE)) {
            final String id = elmt.getAttribute(ModelioPreferenceDialog.ATTR_ID);
            final String name = elmt.getAttribute(ModelioPreferenceDialog.ATTR_NAME);
        
            if (isEmpty(id) || isEmpty(name)) {
                Preferences.LOG.warning("missing id and/or name: %s", elmt.getNamespaceIdentifier());
                continue;
            }
        
            PreferenceNode pn = null;
            final String clazz = elmt.getAttribute(ModelioPreferenceDialog.ATTR_CLASS);
            if (clazz != null) {
                IPreferencePage page = null;
                try {
                    final String prefPageURI = getClassURI(elmt.getNamespaceIdentifier(), clazz);
                    final Object object = factory.create(prefPageURI, context);
                    if (!(object instanceof IPreferencePage)) {
                        Preferences.LOG.error("Expected instance of IPreferencePage: %s", clazz);
                        continue;
                    }
                    page = (IPreferencePage) object;
                } catch (final ClassNotFoundException e) {
                    Preferences.LOG.error(e);
                    continue;
                }
                ContextInjectionFactory.inject(page, context);
                if ((page.getTitle() == null || page.getTitle().isEmpty()) && name != null) {
                    page.setTitle(name);
                }
        
                final String iconAttr = elmt.getAttribute(ModelioPreferenceDialog.ATTR_ICON);
                final ImageDescriptor icon = iconAttr != null ? AbstractUIPlugin.imageDescriptorFromPlugin(elmt.getContributor().getName(), iconAttr) : null;
        
                pn = new PreferenceNode(id, page.getTitle(), icon, null);
                pn.setPage(page);
            } else {
                Preferences.LOG.error("No class defined for preference page: %s", id);
                continue;
            }
            if (isEmpty(elmt.getAttribute(ModelioPreferenceDialog.ATTR_CATEGORY))) {
                rootPreferences.add(pn);
            } else {
                final IPreferenceNode parent = findNode(rootPreferences, elmt.getAttribute(ModelioPreferenceDialog.ATTR_CATEGORY));
                if (parent == null) {
                    rootPreferences.add(pn);
                } else {
                    parent.add(pn);
                }
            }
        }
        
        final PreferenceManager pm = new PreferenceManager();
        for (final IPreferenceNode pn : rootPreferences) {
            pm.addToRoot(pn);
        }
        return pm;
    }

    @objid ("8badbb5b-70ab-487b-8fce-0572c801c795")
    private static boolean isEmpty(final String value) {
        return value == null || value.trim().isEmpty();
    }

    @objid ("ea571be5-8067-4949-9647-e2c692636ae8")
    private static IPreferenceNode findNode(final Set<IPreferenceNode> rootPreferences, final String categoryId) {
        for (final IPreferenceNode o : rootPreferences) {
            if (o.getId().equals(categoryId)) {
                return o;
            }
        }
        return null;
    }

    @objid ("152a2e8f-3f51-42d9-97dd-33e97d7729c1")
    private static String getClassURI(final String definingBundleId, final String spec) throws ClassNotFoundException {
        if (spec.startsWith("bundleclass:")) { // $NON-NLS-1$
            return spec;
        }
        return "bundleclass://" + definingBundleId + '/' + spec;
    }

    @objid ("6c004e37-97ed-452c-be21-636bf639f637")
    @Override
    protected Point getInitialSize() {
        return new Point(860, 540);
    }

    @objid ("83e14b26-7d50-4d46-843a-400215f470c7")
    @Override
    protected void configureShell(final Shell newShell) {
        super.configureShell(newShell);
        newShell.setMinimumSize(860, 540);
    }

}
