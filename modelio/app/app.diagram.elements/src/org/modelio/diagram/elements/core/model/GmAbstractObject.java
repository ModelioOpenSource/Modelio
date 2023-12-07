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
package org.modelio.diagram.elements.core.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.IDynamicStyler;
import org.modelio.diagram.elements.plugin.DiagramElements;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.AbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.IStyleChangeListener;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.core.view.LegacyStyleKeyProviderSymbolViewModel;

/**
 * Abstract class for all graphic models.
 * <p>
 * All graphic models should inherit from this class instead of directly implementing {@link IGmObject}.
 * 
 * @author cmarin
 */
@objid ("8078f557-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmAbstractObject implements IGmObject, IStyleChangeListener {
    @objid ("8078f566-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * The suffix of the minor version property to use to read and write the minor version.
     * <p>
     * Use {@link #readMinorVersion(IDiagramReader, String)} and {@link #writeMinorVersion(IDiagramWriter, String, int)} instead of directly using this constant.
     */
    @objid ("91ea33e5-1e83-11e2-8cad-001ec947c8cc")
    private static final String MINOR_VERSION_PROPERTY = "version";

    /**
     * Current minor version of GmAbstractObject. Defaults to 0.
     */
    @objid ("8078f563-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    /**
     * The diagram owning this diagram element.
     */
    @objid ("8078f55f-1dec-11e2-8cad-001ec947c8cc")
    private IGmDiagram diagram;

    /**
     * Stores the position and/or the size of the element in the diagram. Is usually a {@link org.eclipse.draw2d.geometry.Rectangle Rectangle}.
     */
    @objid ("8078f559-1dec-11e2-8cad-001ec947c8cc")
    private Object layoutData;

    /**
     * Listeners of all property change events.
     */
    @objid ("8078f55b-1dec-11e2-8cad-001ec947c8cc")
    private PropertyChangeSupport listeners;

    /**
     * Style of the graphic element.
     */
    @objid ("8078f55d-1dec-11e2-8cad-001ec947c8cc")
    private IStyle style;

    /**
     * Creates an instance ready to be used.
     * @param diagram the diagram where the object will be.
     */
    @objid ("8078f568-1dec-11e2-8cad-001ec947c8cc")
    public  GmAbstractObject(IGmDiagram diagram) {
        this.diagram = diagram;
        this.listeners = new PropertyChangeSupport(this);
        
    }

    /**
     * Creates an empty {@link GmAbstractObject} that will be deserialized.
     */
    @objid ("8078f56c-1dec-11e2-8cad-001ec947c8cc")
    public  GmAbstractObject() {
        this.listeners = new PropertyChangeSupport(this);
    }

    @objid ("8078f56f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        assert !Arrays.asList(this.listeners.getPropertyChangeListeners()).contains(listener) : this + " contains " + Arrays.toString(this.listeners.getPropertyChangeListeners());
        
        this.listeners.addPropertyChangeListener(listener);
        
    }

    /**
     * Delete the graphic model from the diagram.
     * <p>
     * This method may be redefined by subclasses. In this case the inherited method must be called last.
     */
    @objid ("8078f573-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void delete() {
        firePropertyChange(IGmObject.PROPERTY_DELETE, null, null);
        if (this.style != null) {
            this.style.dispose();
            this.style = null;
        }
        
        if (this.diagram != null) {
            this.diagram.removeGraphicModel(this);
            // since 3.7, nullify the diagram to invalidate the model
            removedFromDiagram();
        }
        
    }

    /**
     * Get the diagram containing this element.
     * <p>
     * May return <i>null</i> if the graphic model is not valid anymore.
     * @return the diagram.
     */
    @objid ("8078f577-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IGmDiagram getDiagram() {
        return this.diagram;
    }

    @objid ("8078f57d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getLayoutData() {
        return this.layoutData;
    }

    @objid ("807b57c9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmAbstractObject.MAJOR_VERSION;
    }

    /**
     * Return true if the diagram owning this graphic model is editable by the user.
     */
    @objid ("807b57d2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isUserEditable() {
        return this.diagram != null && this.diagram.isUserEditable();
    }

    /**
     * By default all GmAbstractObjects are internal.
     */
    @objid ("8078f588-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean isExternal(IDiagramWriter out) {
        return false;
    }

    @objid ("8078f58f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        int readVersion = readMinorVersion(in, "GmAbstractObject.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert false : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("8078f593-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.listeners.removePropertyChangeListener(listener);
    }

    @objid ("807b57ad-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setLayoutData(Object layoutData) {
        Object oldData = this.layoutData;
        this.layoutData = layoutData;
        firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, oldData, layoutData);
        
    }

    /**
     * Called when a property of the style of the element is modified.
     * <p>
     * The element should then update itself from the style change.
     */
    @objid ("807b57b1-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        firePropertyChange(IGmObject.PROPERTY_STYLE, null, getDisplayedStyle());
    }

    /**
     * Called when a style completely changed .
     * <p>
     * The element should then update itself completely from the style.
     */
    @objid ("807b57b7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void styleChanged(IStyle changedStyle) {
        firePropertyChange(IGmObject.PROPERTY_STYLE, null, changedStyle);
    }

    @objid ("bca285a5-e582-4b21-8041-191f61651ea8")
    @Override
    public String toString() {
        return String.format("%s [layout=%s]", getClass().getSimpleName(), getLayoutData());
    }

    @objid ("807b57bc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        out.writeProperty("layoutData", getLayoutData());
        out.writeProperty("Style", this.style);
        
        writeMinorVersion(out, "GmAbstractObject.", GmAbstractObject.MINOR_VERSION);
        
    }

    /**
     * Instantiate the graphic model style.
     * <p>
     * Called by the {@link #getPersistedStyle()} method as lazy initialisation.
     * <p>
     * Must be redefined to create a style or to return <tt>null<tt/> if
     * {@link #getPersistedStyle()} is redefined to return another style.
     * &#64;param aDiagram the diagram where the object will be
     * &#64;return the created style or <tt>null</tt> if the creation is postponed
     */
    @objid ("807b57c0-1dec-11e2-8cad-001ec947c8cc")
    protected abstract IStyle createStyle(IGmDiagram aDiagram);

    @objid ("807b57c4-1dec-11e2-8cad-001ec947c8cc")
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        this.listeners.firePropertyChange(propertyName, oldValue, newValue);
    }

    /**
     * Helper to read the graphic model minor version from the {@value #MINOR_VERSION_PROPERTY} property.
     * @param in a reader to read the version from.
     * @param prefix the prefix : usually the simple name of java class calling this method + ".".
     * @return the read version, defaults to 0 if not found
     */
    @objid ("9ff42b82-711e-421e-ab1d-e0241a82c913")
    protected static final int readMinorVersion(IDiagramReader in, String prefix) {
        // Read version, defaults to 0 if not found
        Object versionProperty = in.readProperty(prefix + GmAbstractObject.MINOR_VERSION_PROPERTY);
        int readVersion = versionProperty == null ? 0 : ((Integer) versionProperty).intValue();
        return readVersion;
    }

    /**
     * Helper method to write the graphic model minor version.
     * @param out the writer to use
     * @param prefix the prefix to use. Usually the java simple name of the class calling this method. Use the same as the matching {@link #readMinorVersion(IDiagramReader, String)}.
     * @param theMinorVersion the minor version to write
     */
    @objid ("e4f71972-f23b-443a-8962-874224a10696")
    protected static final void writeMinorVersion(IDiagramWriter out, String prefix, int theMinorVersion) {
        // Write the version if different of 0.
        if (theMinorVersion != 0) {
            out.writeProperty(prefix + GmAbstractObject.MINOR_VERSION_PROPERTY, Integer.valueOf(theMinorVersion));
        }
        
    }

    @objid ("807b57ce-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(final IDiagramReader in) {
        this.layoutData = in.readProperty("layoutData");
        this.style = (IStyle) in.readProperty("Style");
        this.diagram = (IGmDiagram) in.getRoot();
        
        if (this.style != null) {
            this.style.addListener(this);
        }
        
    }

    /**
     * Get the graphical element style.
     * <p>
     * The style contains many properties such has the foreground and background color, the font and some display options. These properties are displayed and editable in a properties tab.
     * </p>
     * <p>
     * Defined final to make sure that lazy initialization is used.
     * </p>
     * @return the graphical element style.
     */
    @objid ("599433eb-53d0-4c57-8474-9a84537c38c1")
    @Override
    public final IStyle getPersistedStyle() {
        // Lazy initialization.
        // Diagram's style is needed as "base" style,
        // but during deserialization it might not yet
        // be available.
        if (this.style == null && getDiagram() != null) {
            this.style = createStyle(getDiagram());
            this.style.addListener(this);
        }
        return this.style;
    }

    @objid ("17704991-b930-4b67-ae46-16d3a72c61ce")
    @Override
    public final IStyle getDisplayedStyle() {
        if (this instanceof GmModel && getDiagram() != null) {
            IDynamicStyler dynamicStyler = getDiagram().getDynamicStyler();
            if (dynamicStyler != null) {
                try {
                    return dynamicStyler.getStyle((GmModel) this, getPersistedStyle());
                } catch (NoClassDefFoundError e) {
                    // Module has probably stopped, ignore current dynamic styler to avoid the diagram being broken
                    DiagramElements.LOG.error("Invalid dynamic styler : " + dynamicStyler);
                }
            }
        }
        return getPersistedStyle();
    }

    /**
     * This implementation is just here to avoid compilation errors until everybody has an implementation.
     * <p>
     * TODO This implementation is to be deleted before Modelio 3.7 release.
     */
    @objid ("f2ddf749-f32b-4a4c-9fd0-d003c73d014a")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return new LegacyStyleKeyProviderSymbolViewModel(getLegacyStyleKeyProvider("KEYS"), getDiagram().getPersistedStyle());
    }

    /**
     * Look by reflection for a java field with the given name typed {@link AbstractStyleKeyProvider}
     * <p>
     * If no field with the given name is found, look for any field typed {@link AbstractStyleKeyProvider}.
     * @param name the field name
     * @return the found field value , or any {@link AbstractStyleKeyProvider} field value.
     * @deprecated for 3.6- compatibility only
     * @since 3.7
     */
    @objid ("b43e3d5f-80fa-47a6-b6a5-6a3c8c590811")
    @Deprecated
    protected final AbstractStyleKeyProvider getLegacyStyleKeyProvider(String name) {
        AbstractStyleKeyProvider any = null;
        for (Field field : getClass().getDeclaredFields()) {
            if (AbstractStyleKeyProvider.class.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                try {
                    AbstractStyleKeyProvider read = (AbstractStyleKeyProvider) field.get(this);
                    if (read != null && any == null) {
                        any = read;
                    }
                    if (field.getName().equalsIgnoreCase(name)) {
                        return read;
                    }
                } catch (@SuppressWarnings ("unused") IllegalArgumentException | IllegalAccessException e) {
                    // ignore
                }
            }
        
        }
        return any;
    }

    /**
     * Tells whether the graphic model is usable or not.
     * <p>
     * A graphic model is usable if its diagram field has not been nullified.
     * </p>
     * @return <code>true</code> if the graphic model is valid, <code>false</code> otherwise.
     * @since Modelio 3.7
     */
    @objid ("a7692216-481f-4c2f-a772-47433ef5f2da")
    public boolean isValid() {
        return getDiagram() != null;
    }

    /**
     * Change the diagram containing this element.
     * <p>
     * This method should be called only when this graphic model is moved in another diagram. In this case the caller must ensure owned graphic model are moved too.
     * @see #updateDiagram()
     * @param newDiagram the diagram.
     */
    @objid ("a8abb3cf-11bf-4538-9f73-456ae00e8a53")
    protected final void moveToDiagram(IGmDiagram newDiagram) {
        final IGmDiagram oldDiagram = getDiagram();
        
        if (oldDiagram == newDiagram) {
            return;
        }
        
        oldDiagram.removeGraphicModel(this);
        this.diagram = newDiagram;
        newDiagram.addGraphicModel(this);
        
        IGmDiagram oldRoot = IGmDiagram.getRoot(oldDiagram);
        oldRoot.refreshAllGmReferences();
        
        IGmDiagram newRoot = IGmDiagram.getRoot(newDiagram);
        if (newRoot != oldRoot) {
            newRoot.refreshAllGmReferences();
        }
        
    }

    @objid ("8fa7846b-2ce8-4f91-8d2f-6704ee084c28")
    @Override
    public final void removedFromDiagram() {
        this.diagram = null;
        
        // Since 5.4.1 27/10/2023 : also dispose the style to avoid memory leaks
        if (this.style != null) {
            this.style.dispose();
            this.style = null;
        }
        
    }

}
