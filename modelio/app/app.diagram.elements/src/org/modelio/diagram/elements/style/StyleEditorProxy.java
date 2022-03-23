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
package org.modelio.diagram.elements.style;

import java.text.MessageFormat;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.IStyleChangeListener;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.api.transactions.ITransactionSupport;

/**
 * Proxy to edit the style of a {@link IGmObject} in a transaction.
 * <p>
 * Calling any setter will first open a transaction, call the initial setter and finally commit the transaction.
 * <p>
 * This proxy should be used instead of directly calling IStyle setters.
 */
@objid ("ac4c95f0-55b7-11e2-877f-002564c97630")
public class StyleEditorProxy implements IStyle {
    @objid ("ac4c95f4-55b7-11e2-877f-002564c97630")
    private final IStyle edited;

    @objid ("1c6e5d05-58d7-11e2-8bfd-001ec947ccaf")
    private final IGmDiagram diagram;

    /**
     * Constructor
     * @param editedGraphic The graphic element whose style is to be modified.
     */
    @objid ("ac4c95fc-55b7-11e2-877f-002564c97630")
    public  StyleEditorProxy(IGmObject editedGraphic) {
        IStyle style = editedGraphic.getDisplayedStyle();
        this.edited = style;
        this.diagram = editedGraphic.getDiagram();
        
    }

    @objid ("ac4e1c7e-55b7-11e2-877f-002564c97630")
    @Override
    public void addListener(IStyleChangeListener l) {
        this.edited.addListener(l);
    }

    @objid ("ac4e1c84-55b7-11e2-877f-002564c97630")
    @Override
    public boolean getBoolean(StyleKey propertyKey) {
        return this.edited.getBoolean(propertyKey);
    }

    @objid ("ac4e1c8c-55b7-11e2-877f-002564c97630")
    @Override
    public IStyle getCascadedStyle() {
        return this.edited.getCascadedStyle();
    }

    @objid ("ac4e1c93-55b7-11e2-877f-002564c97630")
    @Override
    public Color getColor(StyleKey propertyKey) {
        return this.edited.getColor(propertyKey);
    }

    @objid ("ac4e1c9b-55b7-11e2-877f-002564c97630")
    @Override
    public Font getFont(StyleKey propertyKey) {
        return this.edited.getFont(propertyKey);
    }

    @objid ("ac4e1ca3-55b7-11e2-877f-002564c97630")
    @Override
    public int getInteger(StyleKey propertyKey) {
        return this.edited.getInteger(propertyKey);
    }

    @objid ("ac4e1cab-55b7-11e2-877f-002564c97630")
    @Override
    public Set<StyleKey> getLocalKeys() {
        return this.edited.getLocalKeys();
    }

    @objid ("ac4e1cb4-55b7-11e2-877f-002564c97630")
    @Override
    public <T> T getProperty(StyleKey propertyKey) {
        return this.edited.getProperty(propertyKey);
    }

    @objid ("ac4e1cbd-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isLocal(StyleKey propertyKey) {
        return this.edited.isLocal(propertyKey);
    }

    @objid ("ac4fa342-55b7-11e2-877f-002564c97630")
    @Override
    public void normalize() {
        final Runnable r = () -> getEditedStyle().normalize();
        run("Read style from stream.", r);
        
    }

    @objid ("ac4fa31d-55b7-11e2-877f-002564c97630")
    @Override
    public void removeListener(IStyleChangeListener l) {
        this.edited.removeListener(l);
    }

    @objid ("ac4fa323-55b7-11e2-877f-002564c97630")
    @Override
    public void removeProperty(final StyleKey key) {
        final Runnable r = () -> getEditedStyle().removeProperty(key);
        
        run("Reset '" + key.getId() + "' property.", r);
        
    }

    @objid ("ac4fa32a-55b7-11e2-877f-002564c97630")
    @Override
    public void reset() {
        final Runnable r = () -> getEditedStyle().reset();
        
        run("Reset style.", r);
        
    }

    @objid ("ac4fa32d-55b7-11e2-877f-002564c97630")
    @Override
    public void setCascadedStyle(final IStyle style) {
        final Runnable r = () -> getEditedStyle().setCascadedStyle(style);
        
        run("Set cascaded style.", r);
        
    }

    @objid ("ac4fa334-55b7-11e2-877f-002564c97630")
    @Override
    public void setProperty(final StyleKey key, final Object value) {
        final Runnable r = () -> getEditedStyle().setProperty(key, value);
        
        run(MessageFormat.format("Set ''{0}'' property to ''{1}''.", key.getId(), value), r);
        
    }

    /**
     * @return the edited style.
     */
    @objid ("1c70bf42-58d7-11e2-8bfd-001ec947ccaf")
    IStyle getEditedStyle() {
        return this.edited;
    }

    /**
     * Run the work in a Modelio transaction.
     * @param actionName A name for the transaction.
     */
    @objid ("ac4fa33d-55b7-11e2-877f-002564c97630")
    private void run(String actionName, Runnable toRun) {
        final ITransactionSupport transactionManager = this.diagram.getModelManager().getModelingSession().getTransactionSupport();
        
        // DiagramStyles.LOG.debug(actionName);
        
        try (ITransaction transaction = transactionManager.createTransaction(actionName)) {
            toRun.run();
            this.diagram.save(false);
            transaction.commit();
        }
        
    }

    @objid ("7080db81-87db-40b1-b4b6-8e8239f7c2e4")
    @Override
    public void normalize(StyleKey skey) {
        final Runnable r = () -> getEditedStyle().normalize(skey);
        run("Read style from stream.", r);
        
    }

    @objid ("fa7d2e6a-2d0c-4761-897d-b21b519c6267")
    @Override
    public boolean isDynamicValue(StyleKey propertyKey) {
        return this.edited.isDynamicValue(propertyKey);
    }

    @objid ("f08da7b3-a121-4231-81f2-f62963fecac1")
    @Override
    public IStyle getBaseStyle() {
        return this.edited.getBaseStyle();
    }

    @objid ("fd592e78-35a1-41df-a4ef-7d454e5d2f63")
    @Override
    public void setBaseStyle(IStyle baseStyle) {
        run("Set cascaded style.", () -> getEditedStyle().setBaseStyle(baseStyle));
    }

    /**
     * @param diagram the edited diagram
     * @param edited the style to edit
     */
    @objid ("e94d214b-feb9-47f0-b4f9-45460941edf2")
    public  StyleEditorProxy(IGmDiagram diagram, IStyle edited) {
        this.diagram = diagram;
        this.edited = edited;
        
    }

    @objid ("0511243c-e0e9-4eaf-97c7-3abb9e009284")
    @Override
    public void dispose() {
        this.edited.dispose();
    }

}
