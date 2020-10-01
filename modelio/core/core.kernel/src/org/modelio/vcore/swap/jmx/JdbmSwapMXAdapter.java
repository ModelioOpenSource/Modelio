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

package org.modelio.vcore.swap.jmx;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.ISwap;
import org.modelio.vcore.smkernel.SmObjectData;
import org.modelio.vcore.swap.JdbmSwap;

/**
 * Adapter from {@link JdbmSwap} to {@link ISwapMXBean}.
 */
@objid ("0c180005-4931-11e2-91c9-001ec947ccaf")
public class JdbmSwapMXAdapter implements ISwap, ISwapMXBean {
    @objid ("dcbe8766-493b-11e2-91c9-001ec947ccaf")
    private long swapCount;

    @objid ("dcbe8767-493b-11e2-91c9-001ec947ccaf")
    private long restoreCount;

    @objid ("dcbe8758-493b-11e2-91c9-001ec947ccaf")
    private JdbmSwap swap;

    /**
     * Initialize the swap adapter.
     * 
     * @param swap the real swap.
     */
    @objid ("dcbe8759-493b-11e2-91c9-001ec947ccaf")
    public JdbmSwapMXAdapter(JdbmSwap swap) {
        this.swap= swap;
    }

    @objid ("dcbe875d-493b-11e2-91c9-001ec947ccaf")
    @Override
    public int getCommitFrequence() {
        return JdbmSwap.getCommitFreq();
    }

    @objid ("dcbe8762-493b-11e2-91c9-001ec947ccaf")
    @Override
    public void setCommitFrequence(int value) {
        JdbmSwap.setCommitFreq(value);
    }

    @objid ("dcbe8768-493b-11e2-91c9-001ec947ccaf")
    @Override
    public long getSwapCount() {
        return this.swapCount;
    }

    @objid ("dcbe876d-493b-11e2-91c9-001ec947ccaf")
    @Override
    public long getRestoreCount() {
        return this.restoreCount;
    }

    @objid ("dcbe8772-493b-11e2-91c9-001ec947ccaf")
    @Override
    public void swap(SmObjectData data) {
        this.swap.swap(data);
        this.swapCount++;
    }

    @objid ("dcbe8776-493b-11e2-91c9-001ec947ccaf")
    @Override
    public SmObjectData restore(String uuid) {
        this.restoreCount++;
        return this.swap.restore(uuid);
    }

    @objid ("dcbe877c-493b-11e2-91c9-001ec947ccaf")
    @Override
    public void close() {
        this.swap.close();
    }

}
