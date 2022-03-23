/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.module.mda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.scope.ElementScope;
import org.modelio.api.plugin.Api;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * The MDA expert tool can answer questions about dependencies between stereotyped metaclass or elements.
 * </p>
 * 
 * @since 3.4
 */
@objid ("80a74076-5cc9-4ff8-b25e-d33002d0fe7c")
public interface IMdaExpert {
    @objid ("99fecd0b-913b-4ae9-9804-97099509bcf4")
    boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, MObject from, MObject to);

    @objid ("c4d1bcb2-baf4-4a0b-8eac-40027a38cafc")
    boolean canLink(Stereotype linkStereotype, MClass linkMetaclass, final MClass fromMetaclass, final MClass toMetaclass);

    /**
     * @since 3.8
     */
    @objid ("0fd78025-ebf4-4232-96bd-43949b1c78d1")
    default boolean canLink(ElementScope linkScope, ElementScope fromScope, ElementScope toScope) {
        return canLink(linkScope.getStereotype(), linkScope.getMetaclass(), fromScope.getMetaclass(), toScope.getMetaclass());
    }

    @objid ("d3a1e7c7-c497-4eca-891d-324f5455cd0d")
    boolean canSource(Stereotype linkStereotype, MClass linkMetaclass, MClass fromMetaclass);

    @objid ("e877352f-c147-4f5d-95e0-6073322e667c")
    boolean canSource(Stereotype linkStereotype, MObject linkMetaclass, MObject from);

    /**
     * @since 3.8
     */
    @objid ("1ce65f2c-de71-4d49-9867-53fe6a4020c0")
    default boolean canSource(ElementScope linkScope, MObject from) {
        Api.LOG.debug("%s.canSource(ElementScope, MObject) not implemented.", getClass());
        return canSource(linkScope.getStereotype(), linkScope.getMetaclass(), from.getMClass());
    }

    /**
     * @since 3.8
     */
    @objid ("c28f143a-bc18-4c75-997a-772e78d3b282")
    default boolean canSource(ElementScope linkScope, ElementScope fromScope) {
        return canSource(linkScope.getStereotype(), linkScope.getMetaclass(), fromScope.getMetaclass());
    }

    @objid ("e4446186-5606-47b3-b4a0-86a94d62f062")
    boolean canTarget(Stereotype linkStereotype, MClass linkMetaclass, MClass toMetaclass);

    @objid ("f3836e07-62fa-421c-aae0-4f274189770e")
    boolean canTarget(Stereotype linkStereotype, MObject linkMetaclass, MObject to);

    /**
     * @since 3.8
     */
    @objid ("85425659-4004-46e0-a3fe-fda7d674b4db")
    default boolean canTarget(ElementScope linkScope, ElementScope toScope) {
        return canTarget(linkScope.getStereotype(), linkScope.getMetaclass(), toScope.getMetaclass());
    }

    /**
     * Indicates whether or not several instances of a MethodologicalLink can be used at the same time on an element.
     * <p>
     * Default value is <code>true</code>.
     * </p>
     * @param linkStereotype a Stereotype defined on {@link MethodologicalLink}.
     * @return <code>true</code> if several instances can be used, <code>false</code> otherwise.
     * @since 3.8
     */
    @objid ("3153dc0f-b7f6-4642-9a04-d810dd320d44")
    default boolean isMultiple(Stereotype linkStereotype) {
        return true;
    }

    /**
     * Get the possible target metaclasses of a stereotyped link.
     * @param linkStereotype a stereotype applicable on a link metaclass. Must not be <code>null</code>.
     * @param sourceMetaclass the source of the link. Must not be <code>null</code>.
     * @return a list of metaclass that can be used as target for the link in this configuration.
     * @since 3.8
     */
    @objid ("b5ca3dac-3be5-4ba0-ac45-cef542aa4474")
    default Collection<MClass> getPossibleTargetMetaclasses(Stereotype linkStereotype, MClass sourceMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        MExpert mExpert = metamodel.getMExpert();
        MClass linkMetaclass = metamodel.getMClass(linkStereotype.getBaseClassName());
        return linkMetaclass.getDependencies(true)
                .stream()
                .filter(dep -> ((SmDependency) dep).hasDirective(SmDirective.SMCDLINKTARGET))
                .map(dep -> dep.getTarget())
                .filter(targetMetaclass -> mExpert.canLink(linkMetaclass, sourceMetaclass, targetMetaclass))
                .collect(Collectors.<MClass> toList());
        
    }

    /**
     * Get the possible source metaclasses of a stereotyped link.
     * @param linkStereotype a stereotype applicable on a link metaclass. Must not be <code>null</code>.
     * @param targetMetaclass the target of the link. Must not be <code>null</code>.
     * @return a list of metaclass that can be used as source for the link in this configuration.
     * @since 3.8
     */
    @objid ("080e249a-47b7-4c75-9f62-7eceaf2f8575")
    default Collection<MClass> getPossibleSourceMetaclasses(Stereotype linkStereotype, MClass targetMetaclass) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        MExpert mExpert = metamodel.getMExpert();
        MClass linkMetaclass = metamodel.getMClass(linkStereotype.getBaseClassName());
        return linkMetaclass.getDependencies(true)
                .stream()
                .filter(dep -> ((SmDependency) dep).hasDirective(SmDirective.SMCDLINKSOURCE))
                .map(dep -> dep.getTarget())
                .filter(sourceMetaclass -> mExpert.canLink(linkMetaclass, sourceMetaclass, targetMetaclass))
                .collect(Collectors.<MClass> toList());
        
    }

    /**
     * Get the possible source/target combinations for a stereotyped link.
     * @param linkStereotype a stereotype applicable on a link metaclass. Must not be <code>null</code>.
     * @return a map of scopes, where each key is a possible source and its associated values the possible targets.
     * @since 3.8
     */
    @objid ("4677b382-b221-465c-b9b6-49008077b668")
    default Map<ElementScope, List<ElementScope>> getLinkingRules(Stereotype linkStereotype) {
        MMetamodel metamodel = linkStereotype.getMClass().getMetamodel();
        MExpert mExpert = metamodel.getMExpert();
        MClass linkMetaclass = metamodel.getMClass(linkStereotype.getBaseClassName());
        
        List<MClass> possibleSources = linkMetaclass.getDependencies(true)
                .stream()
                .filter(dep -> ((SmDependency) dep).hasDirective(SmDirective.SMCDLINKSOURCE))
                .map(dep -> dep.getTarget())
                .collect(Collectors.<MClass> toList());
        
        List<MClass> possibleTargets = linkMetaclass.getDependencies(true)
                .stream()
                .filter(dep -> ((SmDependency) dep).hasDirective(SmDirective.SMCDLINKTARGET))
                .map(dep -> dep.getTarget())
                .collect(Collectors.<MClass> toList());
        
        Map<ElementScope, List<ElementScope>> ret = new HashMap<>();
        for (MClass possibleSource : possibleSources) {
            List<ElementScope> validTargets = new ArrayList<>();
        
            for (MClass possibleTarget : possibleTargets) {
                if (mExpert.canLink(linkMetaclass, possibleSource, possibleTarget)) {
                    validTargets.add(new ElementScope(possibleTarget, true, null, true));
                }
            }
        
            if (!validTargets.isEmpty()) {
                ret.put(new ElementScope(possibleSource, true, null, true), validTargets);
            }
        }
        return ret;
    }

}
