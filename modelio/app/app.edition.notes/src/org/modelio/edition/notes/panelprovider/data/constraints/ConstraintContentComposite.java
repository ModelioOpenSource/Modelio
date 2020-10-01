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

package org.modelio.edition.notes.panelprovider.data.constraints;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.services.EContextService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.modelio.edition.notes.panelprovider.data.AbstractContentComposite;
import org.modelio.edition.notes.plugin.EditionNotes;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.platform.ui.UIColor;
import org.modelio.vcore.session.api.transactions.ITransaction;
import org.modelio.vcore.session.impl.CoreSession;

@objid ("741530e9-7dfd-407f-98c3-7f6f4948919a")
public class ConstraintContentComposite extends AbstractContentComposite {
    @objid ("95a99bb0-6d82-4f13-bde4-ea3461444bd0")
    private Constraint constraint = null;

    @objid ("273c4e02-7306-4edc-85a8-d30fb67cc019")
    private final Text text;

    @objid ("75035e7d-265e-4305-95c0-1e15df65907f")
    private final Controler controler;

    @objid ("5cd6ab34-6d18-43a8-ab10-f16b82522bb3")
    public ConstraintContentComposite(Composite parentComposite, int style, EContextService contextService) {
        super(parentComposite, style, contextService);
        setLayout(new FillLayout());
        this.text = new Text(this, SWT.BORDER | SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
        this.controler = new Controler(this);
        this.text.addFocusListener(this.controler);
        this.text.addKeyListener(this.controler);
    }

    @objid ("124a8259-0a36-4ac9-9085-a72132116c51")
    @Override
    public Constraint getNoteElement() {
        return this.constraint;
    }

    @objid ("b31acf4d-d3d7-4783-876e-b10ebeed13af")
    @Override
    public Control getControl() {
        return this;
    }

    @objid ("9cd548ff-bc1f-482b-8d1f-4e869ac71322")
    @Override
    public void setInput(final ModelElement aConstraint) {
        this.constraint = (Constraint) aConstraint;
        if (this.constraint != null) {
            this.text.setText(this.constraint.getBody());
            return;
        } else {
            this.text.setText("");
        }
        leaveEdition();
    }

    @objid ("84435744-a9fd-4c6a-a593-9af4d86c82ad")
    @Override
    public void dispose() {
        this.text.dispose();
        super.dispose();
    }

    @objid ("766cb9f6-f91e-4b4d-8691-61ba89a6e86b")
    private void enterEdition() {
        deactivateContexts();
        if (this.constraint != null && this.constraint.isModifiable()) {
            this.text.setBackground(UIColor.TEXT_WRITABLE_BG);
        } else {
            this.text.setBackground(UIColor.TEXT_READONLY_BG);
        }
    }

    @objid ("b3363697-c589-495a-9d07-21ecfe7cfa24")
    private void leaveEdition() {
        if (this.constraint != null && this.constraint.isModifiable()) {
            this.text.setBackground(UIColor.POSTIT_YELLOW);
        } else {
            this.text.setBackground(UIColor.TEXT_READONLY_BG);
        }
        reactivateContexts();
    }

    @objid ("86ea5e10-3fff-4d11-a22c-6bb302a1f03b")
    private static class Controler implements FocusListener, KeyListener {
        @objid ("a5f4a163-1963-4475-a74b-59e4b1ecd375")
        private final ConstraintContentComposite view;

        @objid ("c11088ad-c2ad-4e7f-a911-f509827ffb2d")
        public Controler(ConstraintContentComposite view) {
            this.view = view;
        }

        /**
         * The constraint text has been modified.<br/>
         * The model change event fired by the transaction is in charge of refreshing the whole GUI.
         */
        @objid ("57e4cfc0-9d9c-4e8c-bbb6-9e6b490768bb")
        private void changeContent(Constraint editedConstraint, String s) {
            if (!s.equals(editedConstraint.getBody())) {
                try (ITransaction transaction = CoreSession.getSession(editedConstraint).getTransactionSupport()
                        .createTransaction(EditionNotes.I18N.getString("UpdateConstraint"))) {
                    editedConstraint.setBody(s.replaceAll("\r\n", "\n"));
                    transaction.commit();
                }
            }
        }

        /**
         * On focus gained: enter edition mode if the constraint is modifiable
         */
        @objid ("4e716b2c-c361-4bef-bb60-410f8da3bda2")
        @Override
        public void focusGained(FocusEvent e) {
            final Text text = (Text) e.getSource();
            final Constraint editedconstraint = this.view.getNoteElement();
            if (editedconstraint != null && editedconstraint.getStatus().isModifiable()) {
                this.view.enterEdition();
            }
        }

        @objid ("11bd81fd-8972-49f7-bd41-9dbcea02625b")
        @Override
        public void focusLost(FocusEvent e) {
            final Text text = (Text) e.getSource();
            final Constraint editedConstraint = this.view.getNoteElement();
            changeContent(editedConstraint, text.getText());
            this.view.leaveEdition();
        }

        @objid ("7a67b624-e09a-4b41-9ec8-ef350f86e6e6")
        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == SWT.CR) {
                e.doit = false;
            }
        }

        @objid ("24f192d9-68c8-4468-a047-29d7d2ee0959")
        @Override
        public void keyReleased(KeyEvent e) {
            final Text text = (Text) e.getSource();
            final Constraint note = this.view.getNoteElement();
            
            if (e.keyCode == SWT.ESC) {
                // ESC
                // restore content from note
                text.setText(note.getBody());
                this.view.leaveEdition();
            } else if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == SWT.CR) {
                // CTRL Enter
                changeContent(note, text.getText());
                this.view.leaveEdition();
            } else if ((e.stateMask &= SWT.MOD1) != 0 && e.keyCode == 'a') {
                // CTRL A
                text.selectAll();
            }
        }

    }

}
