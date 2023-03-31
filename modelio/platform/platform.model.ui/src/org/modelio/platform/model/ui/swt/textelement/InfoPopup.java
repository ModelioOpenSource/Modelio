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
package org.modelio.platform.model.ui.swt.textelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/*
 * This class is the companion info popup that describes the current selection in the PopupChooser
 */
@objid ("e7eec8cd-635e-4a9e-877d-68f0f49647a4")
class InfoPopup extends PopupDialog {
    @objid ("92ac8f9a-a6f9-4980-81cf-f8b042ce500a")
    private static final String EMPTY = "";

    /*
     * The description text displayed in the popup.
     */
    @objid ("6c0c6e1f-389e-4724-9940-e0100dba4de6")
    private String contents = EMPTY;

    /*
         * The text control that displays the description.
         */
    @objid ("0b690658-5c78-442c-a82c-32b4c1f0419b")
    private Browser text;

    /*
         * Construct an info-popup with the specified parent.
         */
    @objid ("25bedb9d-d8b3-4a69-b3d5-90c90d027a9c")
     InfoPopup(Shell parent) {
        super(parent, PopupDialog.HOVER_SHELLSTYLE | SWT.RESIZE, /* takefocusOnOpen */false, /* persistSize */false, /* persistLocation */
                false, /* showDialogMenu */false, /* showPersistActions */false, /* titleText */null, /* infoText */null);
        
    }

    @objid ("de418b82-9038-45e6-896b-1a2267f97d77")
    @Override
    protected Control createDialogArea(Composite parent) {
        this.text = new Browser(parent, SWT.NO_FOCUS);
        
        this.text.setBackground(getBackground());
        
        // Use the compact margins employed by PopupDialog.
        final GridData gd = new GridData(GridData.BEGINNING | GridData.FILL_BOTH);
        gd.horizontalIndent = PopupDialog.POPUP_HORIZONTALSPACING;
        gd.verticalIndent = PopupDialog.POPUP_VERTICALSPACING;
        this.text.setLayoutData(gd);
        this.text.setText(this.contents);
        
        // since SWT.NO_FOCUS is only a hint...
        this.text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                // ContentProposalPopup.this.close();
            }
        });
        return this.text;
    }

    /*
         * Position the info popup adjacent to the proposal popup
         */
    @objid ("449a0d47-8510-4001-8e6d-2a4fea1683f6")
    @Override
    protected void adjustBounds() {
        final Rectangle parentBounds = getParentShell().getBounds();
        Rectangle proposedBounds;
        // Try placing the info popup to the right
        Rectangle rightProposedBounds = new Rectangle(parentBounds.x + parentBounds.width + PopupDialog.POPUP_HORIZONTALSPACING,
                parentBounds.y + PopupDialog.POPUP_VERTICALSPACING, parentBounds.width, parentBounds.height);
        rightProposedBounds = getConstrainedShellBounds(rightProposedBounds);
        // If it won't fit on the right, try the left
        if (rightProposedBounds.intersects(parentBounds)) {
            Rectangle leftProposedBounds = new Rectangle(parentBounds.x - parentBounds.width - POPUP_HORIZONTALSPACING - 1,
                    parentBounds.y, parentBounds.width, parentBounds.height);
            leftProposedBounds = getConstrainedShellBounds(leftProposedBounds);
            // If it won't fit on the left, choose the proposed bounds
            // that fits the best
            if (leftProposedBounds.intersects(parentBounds)) {
                if (rightProposedBounds.x - parentBounds.x >= parentBounds.x - leftProposedBounds.x) {
                    rightProposedBounds.x = parentBounds.x + parentBounds.width + PopupDialog.POPUP_HORIZONTALSPACING;
                    proposedBounds = rightProposedBounds;
                } else {
                    leftProposedBounds.width = parentBounds.x - POPUP_HORIZONTALSPACING - leftProposedBounds.x;
                    proposedBounds = leftProposedBounds;
                }
            } else {
                // use the proposed bounds on the left
                proposedBounds = leftProposedBounds;
            }
        } else {
            // use the proposed bounds on the right
            proposedBounds = rightProposedBounds;
        }
        getShell().setBounds(proposedBounds);
        
    }

    /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.dialogs.PopupDialog#getForeground()
         */
    @objid ("14e4d133-f4bb-4179-a83c-c4d4b42c0df5")
    @Override
    protected Color getForeground() {
        return this.text.getDisplay().getSystemColor(SWT.COLOR_INFO_FOREGROUND);
    }

    /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.jface.dialogs.PopupDialog#getBackground()
         */
    @objid ("a2c58ed0-9df1-4657-b13c-4154166a7f62")
    @Override
    protected Color getBackground() {
        return this.text.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND);
    }

    /*
         * Set the text contents of the popup.
         */
    @objid ("3ba982aa-55b8-444d-be8d-a691ddd15baf")
    void setContents(String newContents) {
        if (newContents == null) {
            newContents = EMPTY;
        }
        this.contents = newContents;
        if (this.text != null && !this.text.isDisposed()) {
            this.text.setText(this.contents);
        }
        
    }

    /*
         * Return whether the popup has focus.
         */
    @objid ("68637752-b149-4313-b9e6-4a7f9739aa5b")
    boolean hasFocus() {
        if (this.text == null || this.text.isDisposed()) {
            return false;
        }
        return this.text.getShell().isFocusControl() || this.text.isFocusControl();
    }

}
