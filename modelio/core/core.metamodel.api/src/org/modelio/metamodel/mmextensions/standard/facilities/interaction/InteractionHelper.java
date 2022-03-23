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
package org.modelio.metamodel.mmextensions.standard.facilities.interaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionOperand;
import org.modelio.metamodel.uml.infrastructure.UmlModelElement;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Services to ease navigation in {@link Interaction} models.
 * @author cma
 * @since 3.7.1
 */
@objid ("c7b39350-f9b7-4f59-a130-40c9b8206e1f")
public class InteractionHelper {
    /**
     * Get all {@link InteractionFragment} of the interaction.
     * @return a stream of all {@link InteractionFragment} of the interaction.
     */
    @objid ("752ac436-b047-41d3-b1ac-7ab8ed121440")
    public static Stream<InteractionFragment> allInteractionFragments(Interaction interaction) {
        return interaction.getFragment().stream().flatMap(InteractionHelper::allFragments);
    }

    /**
     * Get all {@link InteractionFragment} of the interaction.
     * @return all {@link InteractionFragment} of the interaction.
     */
    @objid ("3743109f-d40e-4eb1-a156-c6e1a5006a69")
    public static Collection<InteractionFragment> getAllInteractionFragments(Interaction interaction) {
        Collection<InteractionFragment> ret = new ArrayList<>();
        for (InteractionFragment f : interaction.getFragment()) {
            getAllFragments(f, ret);
        }
        return ret;
    }

    /**
     * Returns the {@link Interaction} enclosing the passed element if any, <code>null</code> otherwise.
     * @param el the element which enclosing Interaction is searched.
     * @return the {@link Interaction} enclosing the passed element if any, <code>null</code> otherwise.
     */
    @objid ("f2c504fa-a816-4d73-ae65-2d77f55b5f2d")
    public static Interaction getInteraction(MObject el) {
        MObject composed = el;
        while (composed != null && !(composed instanceof Interaction)) {
            composed = composed.getCompositionOwner();
        }
        return (Interaction) composed;
    }

    /**
     * Get a stream of the given {@link InteractionFragment} and all owned {@link InteractionFragment}.
     * @return a Stream on all {@link InteractionFragment} of the element.
     */
    @objid ("71e0ee24-8c0d-4e43-a2ef-08a7d31ad56c")
    private static Stream<InteractionFragment> allFragments(InteractionFragment obj) {
        return Stream.concat(
                        Stream.of(obj), 
                        OwnedFragmentsGetter.INSTANCE.get(obj).stream().flatMap(InteractionHelper::allFragments));
    }

    @objid ("d9f4235b-57dd-41d6-bd1b-69807345baf5")
    private static void getAllFragments(InteractionFragment obj, Collection<InteractionFragment> coll) {
        if (!coll.contains(obj)) {
            coll.add(obj);
            for (InteractionFragment f : OwnedFragmentsGetter.INSTANCE.get(obj)) {
                getAllFragments(f, coll);
            }
        }
        
    }

    /**
     * Visitor that returns {@link InteractionFragment} owned by an <code>InteractionFragment</code>.
     */
    @objid ("23d1d40b-a5a4-47ea-ae61-83991b3feb3d")
    private static class OwnedFragmentsGetter extends DefaultModelVisitor {
        @objid ("690f4ec4-a5d8-4c86-b693-5cd0bad175b7")
        public static final OwnedFragmentsGetter INSTANCE = new OwnedFragmentsGetter();

        @objid ("cee777bf-a531-4a81-a3b7-79c682a7983c")
        @SuppressWarnings ("unchecked")
        public List<InteractionFragment> get(MObject obj) {
            Object ret = obj.accept(this);
            
            if (! (ret instanceof List)) {
                throw new IllegalArgumentException(String.valueOf(obj));
            }
            return (List<InteractionFragment>) ret;
        }

        @objid ("93e46b86-6060-4bfd-8bdf-93bdff0b1ffa")
        @Override
        public List<InteractionFragment> visitInteraction(Interaction obj) {
            return obj.getFragment();
        }

        @objid ("b7ee0871-843d-49e5-ad73-4b05410e5b04")
        @Override
        public List<InteractionFragment> visitUmlModelElement(UmlModelElement obj) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }

        @objid ("f117fafa-304c-4f5f-ba89-057695a44d20")
        @Override
        public List<InteractionFragment> visitInteractionFragment(InteractionFragment obj) {
            return Collections.emptyList();
        }

        @objid ("da91b0ed-812a-448c-8d2d-9c4427833122")
        @Override
        public List<InteractionFragment> visitInteractionOperand(InteractionOperand obj) {
            return obj.getFragment();
        }

        @objid ("06bb95e6-95d8-4be2-825f-850e49c25bfc")
        @Override
        public List<? extends InteractionFragment> visitCombinedFragment(CombinedFragment obj) {
            return obj.getOperand();
        }

    }

}
