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
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.swt.widgets.Display;

@objid ("7b6a6585-2a77-11e2-9fb9-bc305ba4815c")
public class RTDocument extends Document {
    @objid ("7b6a6586-2a77-11e2-9fb9-bc305ba4815c")
    IDocumentPartitioner currentPartitionner;

    @objid ("7b6a6587-2a77-11e2-9fb9-bc305ba4815c")
    public  RTDocument(IDocumentPartitioner partitionner) {
        this.setCurrentPartionner(partitionner);
    }

    @objid ("7b6a658a-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void replace(int offset, int length, String text) throws BadLocationException {
        if (length > 0) {
            final ITypedRegion[] typedRegions = computePartitioning(offset, length);
            for (final ITypedRegion typedRegion : typedRegions) {
                if (!isWritablePartition(typedRegion)) {
                    return;
                }
            }
        }
        
        if (isWritablePosition(offset) && isWritablePosition(offset + 1)) {
            // setCurrentPartionner(replacePartitionner);
            super.replace(offset, length, text);
            // setCurrentPartionner(stdPartitionner);
        } else {
            Display.getDefault().beep();
        }
        
    }

    @objid ("7b6a6590-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void replace(int offset, int length, String text, long timestamp) throws BadLocationException {
        if (length > 0) {
            final ITypedRegion[] typedRegions = computePartitioning(offset, length);
            for (final ITypedRegion typedRegion : typedRegions) {
                if (!isWritablePartition(typedRegion)) {
                    return;
                }
            }
        }
        
        if (isWritablePosition(offset) && isWritablePosition(offset + 1)) {
            // setCurrentPartionner(replacePartitionner);
            super.replace(offset, length, text, timestamp);
            // setCurrentPartionner(stdPartitionner);
        } else {
            Display.getDefault().beep();
        }
        
    }

    @objid ("7b6a6597-2a77-11e2-9fb9-bc305ba4815c")
    protected boolean isWritablePosition(int offset) {
        try {
            return isWritablePartition(this.getPartition(offset));
        } catch (final BadLocationException e) {
            e.printStackTrace();
            return true;
        }
        
    }

    @objid ("7b6a659b-2a77-11e2-9fb9-bc305ba4815c")
    protected boolean isWritablePartition(ITypedRegion r) {
        return ! "_OBJINGID".equals(r.getType());
    }

    /**
     * Sets the partitioner to use for this document. If any previous partitioner was used, it is disconnected first.
     * @param partitionner the partitioner to use for this document.
     */
    @objid ("7b6a65a0-2a77-11e2-9fb9-bc305ba4815c")
    public void setCurrentPartionner(IDocumentPartitioner partitionner) {
        if (this.currentPartitionner != null) {
            this.currentPartitionner.disconnect();
        }
        this.currentPartitionner = partitionner;
        this.currentPartitionner.connect(this);
        this.setDocumentPartitioner(partitionner);
        
    }

}
