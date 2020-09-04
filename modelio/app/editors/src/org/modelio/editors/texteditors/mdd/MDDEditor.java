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

package org.modelio.editors.texteditors.mdd;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.modelio.editors.texteditors.IDocumentEditor;
import org.modelio.editors.texteditors.input.IDocumentInput;
import org.modelio.editors.texteditors.mdd.partitions.MDDPartitionScanner;
import org.modelio.editors.texteditors.mdd.partitions.MDDPartitionTypes;
import org.modelio.editors.texteditors.mdd.partitions.MDDReplacePartitionScanner;
import org.modelio.ui.UIColor;

@objid ("7b594e69-2a77-11e2-9fb9-bc305ba4815c")
public class MDDEditor implements IDocumentEditor {
    @objid ("7b594e6a-2a77-11e2-9fb9-bc305ba4815c")
    private static final int VERTICAL_RULER_WIDTH = 12;

    @objid ("725245af-2fd9-11e2-a79f-bc305ba4815c")
    public static final String EDITOR_ID = "org.modelio.editors.texteditors.mdd";

    @objid ("7b594e6c-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentInput input;

    @objid ("7b594e6d-2a77-11e2-9fb9-bc305ba4815c")
    private SourceViewer viewer;

    @objid ("325f845c-2e73-11e2-ab6d-bc305ba4815c")
    private MPart editor;

    @objid ("7b594e6e-2a77-11e2-9fb9-bc305ba4815c")
    @Inject
    public MDDEditor(Composite parent, IDocumentInput input, MPart editor) {
        this.input = input;
        this.editor = editor;
        parent.setLayout(new FillLayout());
        
        // Create Viewer
        this.viewer = createViewer(parent);
        
        // Set Configuration
        this.viewer.configure(new MDDConfiguration());
        
        // Set Partitioner
        IDocumentPartitioner standard = new FastPartitioner(new MDDPartitionScanner(), new String[] {
                MDDPartitionTypes.RO_PARTITION, MDDPartitionTypes.RW_PARTITION, MDDPartitionTypes.TAG_PARTITION,
                MDDPartitionTypes.KEYWORD_PARTITION, MDDPartitionTypes.COMMENT_PARTITION });
        IDocumentPartitioner replace = new FastPartitioner(new MDDReplacePartitionScanner(),
                new String[] { MDDReplacePartitionScanner.RW_PARTITION });
        IDocument document = input.getDocument(new MDDDocument(standard, replace));
        
        this.viewer.setDocument(document);
    }

    @objid ("7b594e73-2a77-11e2-9fb9-bc305ba4815c")
    protected SourceViewer createViewer(Composite parent) {
        VerticalRuler verticalRuler = new VerticalRuler(VERTICAL_RULER_WIDTH);
        int styles = SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION;
        SourceViewer viewer = new SourceViewer(parent, verticalRuler, styles);
        
        Font font = null;
        if (!JFaceResources.getFontRegistry().hasValueFor("JavaEditorFont")) {
            if (SWT.getPlatform().equals("carbon") || SWT.getPlatform().equals("cocoa")) {
                JFaceResources.getFontRegistry().put("JavaEditorFont", new FontData[] { new FontData("Monaco", 11, SWT.NONE) });
            }
        }
        font = JFaceResources.getFontRegistry().get("JavaEditorFont");
        viewer.getTextWidget().setFont(font);
        return viewer;
    }

    @objid ("7b594e78-2a77-11e2-9fb9-bc305ba4815c")
    @Persist
    public void save() {
        this.input.save();
    }

    @objid ("7b594e7b-2a77-11e2-9fb9-bc305ba4815c")
    @Focus
    public void setFocus() {
        this.viewer.getControl().setFocus();
    }

    @objid ("7b594e7e-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IDocumentInput getDocumentInput() {
        return this.input;
    }

    @objid ("c1ef2e5f-2e5d-11e2-a8ff-bc305ba4815c")
    @PreDestroy
    private void destroy() {
        this.input.dispose();
    }

    @objid ("c1ef2e62-2e5d-11e2-a8ff-bc305ba4815c")
    @Override
    public void setReadonlyMode(boolean readOnly) {
        if (readOnly) {
            this.viewer.setEditable(false);
            this.viewer.getTextWidget().setBackground(UIColor.TEXT_READONLY_BG);
            this.editor.setIconURI("platform:/plugin/org.modelio.editors.texteditors/icons/texteditor_ro.png");
        } else {
            this.viewer.setEditable(true);
            this.viewer.getControl().setBackground(UIColor.TEXT_WRITABLE_BG);
            this.editor.setIconURI("platform:/plugin/org.modelio.editors.texteditors/icons/texteditor_rw.png");
        }
    }

}
