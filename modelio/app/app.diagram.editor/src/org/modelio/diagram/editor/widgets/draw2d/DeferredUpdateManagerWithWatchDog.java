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
package org.modelio.diagram.editor.widgets.draw2d;

import java.util.Collection;
import java.util.HashSet;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.DeferredUpdateManager;
import org.eclipse.draw2d.IFigure;
import org.eclipse.swt.graphics.GC;
import org.modelio.diagram.editor.plugin.DiagramEditor;
import org.modelio.diagram.elements.core.figures.FigureDumper;

/**
 * {@link DeferredUpdateManager} with a watch-dog that looks for figure validation cycles and break them.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("43ae97c2-314a-48de-97b7-c17085881145")
public class DeferredUpdateManagerWithWatchDog extends DeferredUpdateManager {
    @objid ("b710a345-11ba-47b2-8040-c9f4f729f88e")
    private boolean validating;

    @objid ("a9523ece-7b7b-4049-ad71-9679a6b93a89")
    private int nbAdded;

    @objid ("c5bc4238-0fd2-4a4e-8706-37ff055c7db2")
    private Collection<IFigure> localQueue = new HashSet<>();

    @objid ("1909ce22-e59e-40f9-86cd-0b0d7307da14")
    @Override
    public synchronized void addInvalidFigure(IFigure f) {
        if (this.localQueue.contains(f)) {
            return;
        }
        
        this.localQueue.add(f);
        if (this.validating && ++this.nbAdded >= 100) {
            if (this.nbAdded == 100) {
                DiagramEditor.LOG.error(new StackOverflowError(String.format(
                        "Figure validation cycle on %s.\n Invalid figures:\n %s",
                        f,
                        new FigureDumper().withOnlyInvalidfigures().run(this.localQueue).get())));
            }
        } else {
            super.addInvalidFigure(f);
        }
        
    }

    @objid ("2c9359bb-3cae-4ed4-85fd-53b962778e84")
    @Override
    public synchronized void performValidation() {
        if (this.validating) {
            return;
        }
        
        this.validating = true;
        try {
            this.localQueue.clear();
            super.performValidation();
        } finally {
            this.validating = false;
            this.nbAdded = 0;
            this.localQueue.clear();
        }
        
    }

    @objid ("edb9fe28-6037-4066-bdc2-a15c5b5e9c4b")
    @Override
    protected void paint(GC gc) {
        super.paint(gc);
    }

}
