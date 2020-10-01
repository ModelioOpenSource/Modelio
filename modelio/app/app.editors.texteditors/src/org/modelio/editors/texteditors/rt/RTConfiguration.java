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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.modelio.editors.texteditors.rt.partitions.RTPartitionTypes;
import org.modelio.editors.texteditors.rt.partitions.TextAttributeManager;

@objid ("7b66bbef-2a77-11e2-9fb9-bc305ba4815c")
public class RTConfiguration extends SourceViewerConfiguration {
    @objid ("7b66bbf0-2a77-11e2-9fb9-bc305ba4815c")
    public RTConfiguration() {
        super();
    }

    @objid ("7b66bbf2-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE, 
                            RTPartitionTypes.KEYWORD_PARTITION, 
                            RTPartitionTypes.OBJINGID_PARTITION, 
                            RTPartitionTypes.COMMENT_PARTITION };
    }

    @objid ("7b66e304-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();
                
        reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
                
        // Default type
        RTDamageRepairer defaultRepairer = new RTDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(null));
        reconciler.setDamager(defaultRepairer, IDocument.DEFAULT_CONTENT_TYPE);
        reconciler.setRepairer(defaultRepairer, IDocument.DEFAULT_CONTENT_TYPE);
        
        // Keywords
        RTDamageRepairer ndr1 = new RTDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(RTPartitionTypes.KEYWORD_PARTITION));
        reconciler.setDamager(ndr1, RTPartitionTypes.KEYWORD_PARTITION);
        reconciler.setRepairer(ndr1, RTPartitionTypes.KEYWORD_PARTITION);
                
        // Comments
        RTDamageRepairer ndr2 = new RTDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(RTPartitionTypes.COMMENT_PARTITION));
        reconciler.setDamager(ndr2, RTPartitionTypes.COMMENT_PARTITION);
        reconciler.setRepairer(ndr2, RTPartitionTypes.COMMENT_PARTITION);
        
        // @objid tags
        RTDamageRepairer ndrTag = new RTDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(RTPartitionTypes.OBJINGID_PARTITION));
        reconciler.setDamager(ndrTag, RTPartitionTypes.OBJINGID_PARTITION);
        reconciler.setRepairer(ndrTag, RTPartitionTypes.OBJINGID_PARTITION);
        return reconciler;
    }

}
