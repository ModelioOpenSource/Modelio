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

package org.modelio.editors.texteditors.mdd;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.modelio.editors.texteditors.mdd.partitions.MDDPartitionTypes;
import org.modelio.editors.texteditors.mdd.partitions.TextAttributeManager;

@objid ("7b564115-2a77-11e2-9fb9-bc305ba4815c")
public class MDDConfiguration extends SourceViewerConfiguration {
    @objid ("7b564116-2a77-11e2-9fb9-bc305ba4815c")
    public MDDConfiguration() {
        super();
    }

    @objid ("7b564118-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public String[] getConfiguredContentTypes(ISourceViewer sourceViewer) {
        return new String[] { IDocument.DEFAULT_CONTENT_TYPE, MDDPartitionTypes.RO_PARTITION, MDDPartitionTypes.RW_PARTITION, MDDPartitionTypes.TAG_PARTITION, MDDPartitionTypes.KEYWORD_PARTITION, MDDPartitionTypes.COMMENT_PARTITION };
    }

    @objid ("7b56411f-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
        PresentationReconciler reconciler = new PresentationReconciler();
                
        reconciler.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
                
        // Read write text
        MDDDamageRepairer ndr = new MDDDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(MDDPartitionTypes.RW_PARTITION));
        reconciler.setDamager(ndr, MDDPartitionTypes.RW_PARTITION);
        reconciler.setRepairer(ndr, MDDPartitionTypes.RW_PARTITION);
                
        // Keywords
        MDDDamageRepairer ndr1 = new MDDDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(MDDPartitionTypes.KEYWORD_PARTITION));
        reconciler.setDamager(ndr1, MDDPartitionTypes.KEYWORD_PARTITION);
        reconciler.setRepairer(ndr1, MDDPartitionTypes.KEYWORD_PARTITION);
                
        MDDDamageRepairer ndr2 = new MDDDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(MDDPartitionTypes.COMMENT_PARTITION));
        reconciler.setDamager(ndr2, MDDPartitionTypes.COMMENT_PARTITION);
        reconciler.setRepairer(ndr2, MDDPartitionTypes.COMMENT_PARTITION);
                
        MDDDamageRepairer ndrRO = new MDDDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(MDDPartitionTypes.RO_PARTITION));
        reconciler.setDamager(ndrRO, MDDPartitionTypes.RO_PARTITION);
        reconciler.setRepairer(ndrRO, MDDPartitionTypes.RO_PARTITION);
                
        MDDDamageRepairer ndrTag = new MDDDamageRepairer(TextAttributeManager.getInstance().getTextAttribute(MDDPartitionTypes.TAG_PARTITION));
        reconciler.setDamager(ndrTag, MDDPartitionTypes.TAG_PARTITION);
        reconciler.setRepairer(ndrTag, MDDPartitionTypes.TAG_PARTITION);
        return reconciler;
    }

}
