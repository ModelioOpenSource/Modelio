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

package org.modelio.editors.texteditors.rt;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.TextViewer;
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
import org.modelio.editors.texteditors.rt.partitions.RTPartitionScanner;
import org.modelio.editors.texteditors.rt.partitions.RTPartitionTypes;
import org.modelio.ui.UIColor;

@objid ("7b6a65a4-2a77-11e2-9fb9-bc305ba4815c")
public class RTEditor implements IDocumentEditor {
    @objid ("7b6a65a5-2a77-11e2-9fb9-bc305ba4815c")
    private static final int VERTICAL_RULER_WIDTH = 12;

    @objid ("7222aa18-2fd9-11e2-a79f-bc305ba4815c")
    public static final String EDITOR_ID = "org.modelio.editors.texteditors.rt";

    @objid ("7b6a65a7-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentInput input;

    @objid ("7b6a65a8-2a77-11e2-9fb9-bc305ba4815c")
    private SourceViewer viewer;

    @objid ("326b6b41-2e73-11e2-ab6d-bc305ba4815c")
    private MPart editor;

    @objid ("7b6a65a9-2a77-11e2-9fb9-bc305ba4815c")
    @Inject
    public RTEditor(Composite parent, IDocumentInput input, MPart editor) {
        this.input = input;
        this.editor = editor;
        parent.setLayout(new FillLayout());
        
        // Create Viewer
        this.viewer = createViewer(parent);
        
        // Set Configuration  
        this.viewer.configure(new RTConfiguration());
        
        // Set Partitioner
        IDocumentPartitioner partitionner = new FastPartitioner(new RTPartitionScanner(), new String[] { RTPartitionTypes.OBJINGID_PARTITION, RTPartitionTypes.KEYWORD_PARTITION, RTPartitionTypes.COMMENT_PARTITION });                    
        IDocument document = input.getDocument(new RTDocument(partitionner));   
          
        this.viewer.setDocument(document);
        editor.setObject(input);
    }

    @objid ("7b6a65af-2a77-11e2-9fb9-bc305ba4815c")
    protected SourceViewer createViewer(Composite parent) {
        VerticalRuler verticalRuler = new VerticalRuler(VERTICAL_RULER_WIDTH);       
        int styles= SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION;
        SourceViewer viewer = new SourceViewer(parent, verticalRuler, styles);   
        
        Font font = null;
        if( ! JFaceResources.getFontRegistry().hasValueFor("JavaEditorFont") ) {
            if( SWT.getPlatform().equals("carbon") || SWT.getPlatform().equals("cocoa") ) {
                JFaceResources.getFontRegistry().put("JavaEditorFont", new FontData[] {new FontData("Monaco",11,SWT.NONE)});
            }
        }
        font = JFaceResources.getFontRegistry().get("JavaEditorFont");
        viewer.getTextWidget().setFont(font);
        return viewer;
    }

    @objid ("7b6bec17-2a77-11e2-9fb9-bc305ba4815c")
    @Focus
    public void setFocus() {
        this.viewer.getControl().setFocus();
    }

    @objid ("7b6bec1a-2a77-11e2-9fb9-bc305ba4815c")
    @Persist
    public void save() {
        this.input.save();
    }

    @objid ("7b6bec1d-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IDocumentInput getDocumentInput() {
        return this.input;
    }

    @objid ("c1e47ff8-2e5d-11e2-a8ff-bc305ba4815c")
    @PreDestroy
    private void destroy() {
        this.input.dispose();
    }

    @objid ("c1e47ffb-2e5d-11e2-a8ff-bc305ba4815c")
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

    @objid ("e5260c7b-c27a-4ca5-9989-fe81377f2351")
    @Override
    public TextViewer getViewer() {
        return this.viewer;
    }

}
