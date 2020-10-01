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

package org.modelio.vcore.session.api.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Basic implementation of {@link IRepositoryChangeEvent}.
 * <p>
 * All returned collections are directly modifiable to fill the event.
 */
@objid ("ef1022e3-aa86-4915-b159-48feeb49920e")
public class BasicRepositoryChangeEvent implements IRepositoryChangeEvent {
    @objid ("a429664b-f085-4700-8272-801e6b036483")
    private final Collection<String> modifiedBlobs;

    @objid ("1ca7fe23-4066-44cd-abca-50bdecda5c0d")
    private final Collection<String> deletedBlobs;

    @objid ("759a251f-8898-44a8-9cf1-0337cd056cd1")
    private final Collection<String> createdBlobs;

    @objid ("c3106658-a1ce-455b-b855-0541f05daeca")
    private final Granularity granularity;

    @objid ("7d26ad18-79b0-4a76-96f2-a45da9128557")
    private final IRepository repository;

    @objid ("df1020a1-6516-4a57-ace2-ab63b59d355f")
    private final List<MRef> deletedElements;

    @objid ("9536c7f8-cc82-4a48-821f-f5b91fe3cc75")
    private final List<MRef> createdElements;

    @objid ("fe7222e2-2a30-4760-8d01-a9a2b7041de6")
    private final List<MRef> modifiedElements;

    /**
     * Get the created elements.
     * 
     * @return the created element references.
     */
    @objid ("05cb524d-bb1d-44dc-8c91-54fbb5c4dc72")
    @Override
    public List<MRef> getCreatedElements() {
        return this.createdElements;
    }

    /**
     * Get the elements deleted.
     * 
     * @return the deleted element references.
     */
    @objid ("f46e65e2-25ed-4bc5-9930-5e63d601735e")
    @Override
    public List<MRef> getDeletedElements() {
        return this.deletedElements;
    }

    /**
     * Get the Modified elements.
     * 
     * @return the updated element references.
     */
    @objid ("49b95944-2dab-4e57-a349-d68f25c4d292")
    @Override
    public List<MRef> getModifiedElements() {
        return this.modifiedElements;
    }

    /**
     * Get the created blobs identifiers.
     * 
     * @return the created blobs identifiers.
     */
    @objid ("593bfa22-64b7-4000-8803-12516317908a")
    @Override
    public Collection<String> getCreatedBlobs() {
        return this.createdBlobs;
    }

    /**
     * Get the deleted blobs identifiers.
     * 
     * @return the deleted blobs identifiers.
     */
    @objid ("8b30f6f3-aa0d-40f3-a560-3f0c8e443638")
    @Override
    public Collection<String> getDeletedBlobs() {
        return this.deletedBlobs;
    }

    /**
     * Get the updated blobs identifiers.
     * 
     * @return the updated blobs identifiers.
     */
    @objid ("e413dd22-4696-4f8b-bf2b-f38a71f3e11f")
    @Override
    public Collection<String> getModifiedBlobs() {
        return this.modifiedBlobs;
    }

    /**
     * @return the modified repository.
     */
    @objid ("582853c9-339c-41ab-84c3-1c921a8ba4e2")
    @Override
    public IRepository getRepository() {
        return this.repository;
    }

    /**
     * Get the precision of the repository change event.
     * 
     * @return the change event precision.
     */
    @objid ("645845b0-1710-4581-93ea-5922bb66c2e1")
    @Override
    public Granularity getGranularity() {
        return this.granularity;
    }

    /**
     * Initialize the event.
     * 
     * @param repo the repository firing the event.
     * @param precision the precision of the event.
     */
    @objid ("32c46be1-f1fa-4ddf-bc88-89b3a8e45d27")
    public BasicRepositoryChangeEvent(IRepository repo, Granularity precision) {
        this.granularity = precision;
        this.repository = repo;
        this.createdBlobs = new ArrayList<>();
        this.modifiedBlobs = new ArrayList<>();
        this.deletedBlobs = new ArrayList<>();
        this.modifiedElements = new ArrayList<>();
        this.createdElements = new ArrayList<>();
        this.deletedElements = new ArrayList<>();
    }

}
