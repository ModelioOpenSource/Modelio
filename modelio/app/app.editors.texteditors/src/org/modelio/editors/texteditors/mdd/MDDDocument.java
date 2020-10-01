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
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPartitioningException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.swt.widgets.Display;
import org.modelio.editors.plugin.TextEditors;
import org.modelio.editors.texteditors.mdd.partitions.MDDPartitionTypes;

/**
 * Default implementation for a Model-Driven Document.
 * 
 * @author pvlaemynck
 */
@objid ("7b57c7d2-2a77-11e2-9fb9-bc305ba4815c")
public class MDDDocument extends Document {
    @objid ("7b57c7d4-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentPartitioner stdPartitionner;

    @objid ("7b57c7d5-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentPartitioner replacePartitionner;

    @objid ("7b57c7d6-2a77-11e2-9fb9-bc305ba4815c")
    private IDocumentPartitioner currentPartitionner;

    /**
     * C'tor.
     */
    @objid ("7b57c7d7-2a77-11e2-9fb9-bc305ba4815c")
    public MDDDocument(IDocumentPartitioner stdPartitionner, IDocumentPartitioner replacePartitionner) {
        defineStdPartitioner(stdPartitionner);
        defineReplacePartitioner(replacePartitionner);
        setCurrentPartionner(stdPartitionner);
    }

    @objid ("7b57c7dc-2a77-11e2-9fb9-bc305ba4815c")
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
            super.replace(offset, length, text);
        } else {
            Display.getDefault().beep();
        }
    }

    @objid ("7b57c7e2-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public void replace(int offset, int length, String text, long timestamp) throws BadLocationException {
        if (length > 0) {
            // Deletion of a piece of text, it might overlap with a non-RW
            // partition
            //if (!isWritablePosition(offset + length) || !isWritablePosition(offset + 1 + 1 - length))
            //return;
            final ITypedRegion[] typedRegions = computePartitioning(offset, length);
            for (final ITypedRegion typedRegion : typedRegions) {
                if (!isWritablePartition(typedRegion)) {
                    return;
                }
            }
        }
        
        if (isWritablePosition(offset) && isWritablePosition(offset + 1)) {
            setCurrentPartionner(this.replacePartitionner);
            super.replace(offset, length, text, timestamp);
            setCurrentPartionner(this.stdPartitionner);
        } else {
            Display.getDefault().beep();
        }
    }

    /**
     * Sets the standard partitioner for this document.
     * 
     * @param partitionner the standard partitioner for this document.
     */
    @objid ("7b57c7e9-2a77-11e2-9fb9-bc305ba4815c")
    private void defineStdPartitioner(IDocumentPartitioner partitionner) {
        this.stdPartitionner = partitionner;
    }

    /**
     * Sets the replace partitioner for this document.
     * 
     * @param partitionner the replace partitioner for this document.
     */
    @objid ("7b57c7ed-2a77-11e2-9fb9-bc305ba4815c")
    private void defineReplacePartitioner(IDocumentPartitioner partitionner) {
        this.replacePartitionner = partitionner;
    }

    /**
     * Sets the partitioner to use for this document.
     * <p>
     * If any previous partitioner was used, it is disconnected first.
     * 
     * @param partitionner the partitioner to use for this document.
     */
    @objid ("7b57c7f1-2a77-11e2-9fb9-bc305ba4815c")
    public void setCurrentPartionner(IDocumentPartitioner partitionner) {
        if (this.currentPartitionner != null) {
            this.currentPartitionner.disconnect();
        }
        this.currentPartitionner = partitionner;
        this.currentPartitionner.connect(this);
        this.setDocumentPartitioner(partitionner);
    }

    @objid ("7b594e58-2a77-11e2-9fb9-bc305ba4815c")
    protected boolean isWritablePosition(int offset) {
        try {
            return isWritablePartition(this.getPartition(offset));
        } catch (final BadLocationException e) {
            TextEditors.LOG.warning(e);
            return false;
        }
    }

    @objid ("7b594e5c-2a77-11e2-9fb9-bc305ba4815c")
    protected boolean isWritablePartition(ITypedRegion r) {
        String regionType = r.getType();
        if (regionType.equals(MDDPartitionTypes.RO_PARTITION) ||
                regionType.equals(MDDPartitionTypes.TAG_PARTITION))
            return false;
        return true;
    }

    /**
     * Overridden to give precedence to an open partition ending at offset over a delimited partition starting at
     * offset.
     */
    @objid ("7b594e61-2a77-11e2-9fb9-bc305ba4815c")
    @Override
    public ITypedRegion getPartition(final int offset) throws BadLocationException {
        ITypedRegion partition = null;
        try {
            partition = getPartition(DEFAULT_PARTITIONING, offset, true);
            Assert.isNotNull(partition, "partition not found");
        } catch (final BadPartitioningException e) {
            throw (BadLocationException) new BadLocationException(e.getMessage()).initCause(e);
        }
        return partition;
    }

}
