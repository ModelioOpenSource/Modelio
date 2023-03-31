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
package org.modelio.platform.ui.dialog;

import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * Helper to create properly layouted edition field components.
 * <p>
 * <h2>Usage</h2>
 * <ol>
 * <li> {@link #PolluxFieldBuilder(Composite)}
 * <li> Call any {@link #createSimpleField(String, Supplier, String)} or {@link #createMultiField(String, Supplier, String)}
 * for each field to create.
 * </ol>
 * @author cmarin
 * @since Alouette 5.3
 */
@objid ("289d006b-8a85-41e4-8079-35a0cf648731")
public class PolluxFieldBuilder {
    @objid ("430a5f0b-8de9-4f80-a53e-e7ff98986897")
    private final Composite parent;

    @objid ("5753e1db-5df1-4d47-9ea2-43828f621048")
    private Label lastCreatedLabel;

    @objid ("6a5f783e-3db8-4a12-b7e1-4679a5cac75e")
    private Control lastCreatedField;

    @objid ("1d13aba8-0f2d-4dab-9deb-8807107d912a")
    private Label lastCreatedCaption;

    /**
     * @param parent the {@link Composite} that will own all created SWT widgets.
     * This {@link Composite} must have been passed to {@link PolluxWidgetConfigurator#configureContainer(Composite)}
     */
    @objid ("03ddf9f7-f8c7-4fc2-95e0-73590a4bb77c")
    public  PolluxFieldBuilder(Composite parent) {
        this.parent = parent;
    }

    /**
     * Create a single line field.
     * <p>
     * After a successful call :
     * <ul>
     * <li> {@link #getLastCreatedLabel()} returns the created field label
     * <li> {@link #getLastCreatedField()} returns the created field editable {@link Control}
     * <li> {@link #getLastCreatedCaption()} returns the created field caption, null if <i>caption</i> was null.
     * </ul>
     * @param <T> the field control type
     * @param label the field label
     * @param creator a Supplier that creates the field {@link Control}
     * @param caption an optional caption
     * @return the field control created by <i>creator</i>
     */
    @objid ("443699d4-2341-40e2-9e82-c59a7b2d2b8a")
    public <T extends Control> T createSimpleField(String label, Supplier<T> creator, String caption) {
        return createField(label, creator, caption, false);
    }

    /**
     * Create a multi line field.
     * <p>
     * After a successful call :
     * <ul>
     * <li> {@link #getLastCreatedLabel()} returns the created field label
     * <li> {@link #getLastCreatedField()} returns the created field editable {@link Control}
     * <li> {@link #getLastCreatedCaption()} returns the created field caption, null if <i>caption</i> was null.
     * </ul>
     * @param <T> the field control type
     * @param label the field label
     * @param creator a Supplier that creates the field {@link Control}
     * @param caption an optional caption
     * @return the field control created by <i>creator</i>
     */
    @objid ("8a34c8b9-5378-4bd4-9082-3b1b841e89c1")
    public <T extends Control> T createMultiField(String label, Supplier<T> creator, String caption) {
        return createField(label, creator, caption, true);
    }

    @objid ("36e99b56-a12c-46f2-9715-72b98788594c")
    private <T extends Control> T createField(String label, Supplier<T> creator, String caption, boolean multiple) {
        // Reset all now in case of exception anywhere later
        this.lastCreatedCaption = null;
        this.lastCreatedField = null;
        this.lastCreatedLabel = null;
        
        // Create field label
        this.lastCreatedLabel = new Label(this.parent, SWT.NONE);
        this.lastCreatedLabel.setText(label);
        
        // Create editable field itself
        T field = creator.get();
        this.lastCreatedField = field;
        
        // Create caption label if provided
        if (caption != null) {
            field.setToolTipText(caption);
        
            this.lastCreatedCaption = new Label(this.parent, SWT.NONE);
            this.lastCreatedCaption.setText(caption);
        }
        
        if (multiple) {
            PolluxWidgetConfigurator.configureMultiField(this.lastCreatedLabel, field, this.lastCreatedCaption);
        } else {
            PolluxWidgetConfigurator.configureSimpleField(this.lastCreatedLabel, field, this.lastCreatedCaption);
        }
        return field;
    }

    @objid ("9f6b000c-814c-4bc9-bef5-5b8fe782f9a3")
    public Label getLastCreatedCaption() {
        return this.lastCreatedCaption;
    }

    @objid ("26a4f576-0c10-4931-a861-78920b15cc4f")
    public Control getLastCreatedField() {
        return this.lastCreatedField;
    }

    @objid ("3082ef9f-916b-428c-8371-6bd121d0eecd")
    public Label getLastCreatedLabel() {
        return this.lastCreatedLabel;
    }

}
