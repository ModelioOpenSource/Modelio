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
package org.modelio.diagram.elements.common.freezone;

import java.util.Collection;
import java.util.Collections;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;

/**
 * Intersection remover that does nothing
 * @author cmarin
 */
@objid ("dc4e19ec-5336-43f7-9356-d150ed250208")
public class NoopLayoutAssistant implements ILayoutAssistant {
    /**
     * A singleton.
     */
    @objid ("c197c2ed-9678-4d12-a1f3-30c663b4717e")
    public static final NoopLayoutAssistant instance = new NoopLayoutAssistant();

    @objid ("957c5fdb-2d6f-4407-9498-f88b567a6b86")
    @Override
    public Collection<BendpointRequest> getBendPointRequests() {
        return Collections.emptyList();
    }

    @objid ("7b089593-4788-40fb-a82c-d177ecc104ae")
    @Override
    public Collection<ChangeBoundsRequest> getNodeRequests() {
        return Collections.emptyList();
    }

    @objid ("2fe7a34c-32c0-4a2c-a91a-fa4528132116")
    @Override
    public void addCommand(Command cmd) {
        // ignore
    }

    @objid ("52d60760-e287-4404-bc11-856e54f8f316")
    @Override
    public Command createExecuteCommand() {
        return null;
    }

    /**
     * Protected constructor.
     * <p>
     * Use the singleton {@link #instance}.
     */
    @objid ("4929274d-c9f6-450e-8f51-f18cad1bc231")
    protected  NoopLayoutAssistant() {
        super();
    }

    @objid ("41bb43bf-6768-4904-8d74-b38ea5ae85fb")
    @Override
    public void addBoundsChange(GraphicalEditPart movedEp, PrecisionRectangle oldRect, PrecisionRectangle newRect) {
        // ignore
    }

}
