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
package org.modelio.gproject.mtools.merge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.vbasic.log.Log;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * Service to merge one or more objects into another.
 * <p>
 * The source objects are deleted in the process.
 * 
 * @author cmarin
 * @since 5.3.1
 */
@objid ("500b6803-29fe-40a7-b587-f3d030467e20")
public class MergeMachine {
    @objid ("9f0543e7-c7e7-4422-9aca-a850a576792d")
    private static final boolean TRACE = false;

    /**
     * if true, target attribute values are replaces by source attributes. Same for ?..1 dependencies.
     */
    @objid ("ac0bae60-0079-4012-84a6-8065261d5742")
    private boolean replaceAttributes;

    /**
     * if true, delete links that will become reflexive after having merged the elements.
     */
    @objid ("17779240-4e66-4361-8507-620fcbf8353b")
    private boolean deleteNewReflexiveLinks;

    /**
     * The element in which {@link #sources} will be merged into.
     */
    @objid ("921edcd5-252e-4916-a395-82d9dbb63b49")
    private final MObject target;

    @objid ("26217057-cab7-4057-959a-a3e2dff9f444")
    private final ICoreSession coreSession;

    @objid ("7b095662-0dca-46ca-9066-9ea1348f6a3a")
    private final Collection<MObject> sources = new HashSet<>(3);

    @objid ("02833e7a-5925-4d37-a73a-a27547ce2fde")
    static Map<Class<? extends MObject>, CustomMerger<? extends MObject>> mergersMap = initCustomMergers();

    /**
     * @param target the object merged objects will be merged into. It is the only object that will remain.
     */
    @objid ("222c62d7-237c-4bc6-927f-9bd07537823d")
    public  MergeMachine(MObject target) {
        this.target = Objects.requireNonNull(target, "target");
        this.coreSession = CoreSession.getSession(target);
        
    }

    /**
     * @param deleteNewReflexiveLinks if true, delete links that will become reflexive after having merged the elements.
     * @return this instance
     */
    @objid ("794f01c5-2f36-4e84-87f2-9e6d01b29a0d")
    public MergeMachine setDeleteNewReflexiveLinks(boolean deleteNewReflexiveLinks) {
        this.deleteNewReflexiveLinks = deleteNewReflexiveLinks;
        return this;
    }

    /**
     * @param replaceAttributes if true, target attribute values are replaces by source attributes. Same for ?..1 dependencies.
     * @return this instance
     */
    @objid ("ad9e89e5-9965-4c89-b71d-c37bd61ac769")
    public MergeMachine setReplaceAttributes(boolean replaceAttributes) {
        this.replaceAttributes = replaceAttributes;
        return this;
    }

    /**
     * Add an element to merge into the {@link #target} element passed to the constructor.
     * @param asource an element to merge into the target
     * @return this instance
     */
    @objid ("1b7fe6e4-e82d-4720-9f0b-01d429eea1c7")
    public MergeMachine addSource(MObject asource) {
        this.sources.add(asource);
        return this;
    }

    /**
     * The source objects are detached to all references, that are attached to the target object.
     * At the end the source objects are deleted.
     */
    @objid ("0a5d88b0-46d2-485f-8328-b4b521aa8c63")
    public void merge() {
        Collection <AbstractDiagram> diagrams = new HashSet<>();
        for (MObject source : this.sources ) {
            if (source instanceof ModelElement) {
                ModelElement el = (ModelElement) source;
                diagrams.addAll( el.getDiagramElement());
            }
        
            merge(source);
        }
        
        DiagramsMerger diagramsMerger = new DiagramsMerger();
        for (AbstractDiagram diag : diagrams) {
            if (diag.isValid()) {
                diagramsMerger.fixDiagram(diag, this.sources, this.target);
            }
        }
        
    }

    /**
     * The original object is detached to all references, that are attached to the target object.
     * At the end the original object is deleted.
     * @param source the original object to merge into target.
     */
    @objid ("fd622a96-dc0e-4054-b391-38fcd5e8060f")
    private void merge(MObject source) {
        Objects.requireNonNull(source, "original");
        if (source.equals(this.target))
            throw new IllegalArgumentException(String.format("Cannot merge %s in itself", source));
        
        if (CoreSession.getSession(source) != this.coreSession)
            throw new IllegalArgumentException(String.format("Cannot merge %s into %s : they belong to different projects.", source, this.target));
        
        
        MClass targetMClass = this.target.getMClass();
        SmClass sourceMClass = (SmClass) source.getMClass();
        
        if (this.replaceAttributes) {
            for (MAttribute att : targetMClass.getAttributes(true)) {
                if (targetMClass != sourceMClass && targetMClass != ((SmAttribute) att).getOwner() && !targetMClass.hasBase(((SmAttribute) att).getOwner())) {
                    // target class does not know the attribute
                    continue;
                }
        
                Object val = source.mGet(att);
                if (val != null) {
                    if (att.getType() == String.class) {
                        this.target.mSet(att, val.toString());
                    } else {
                        this.target.mSet(att, val);
                    }
                }
            }
        }
        
        for (SmDependency sourceDep : sourceMClass.getAllDepDef()) {
        
            if (targetMClass != sourceMClass && targetMClass != sourceDep.getSource() && !targetMClass.hasBase(sourceDep.getSource())) {
                // target metaclass does not know the dependency
                continue;
            }
        
            SmDependency sourceDepSym = sourceDep.getSymetric();
            if (isNavigable(sourceDep) || sourceDepSym == null) {
        
                if (sourceDep.getMaxCardinality() == 1 && ! this.replaceAttributes) {
                    // 0..1 dependency, skip unless "this.replaceAttributes" is active
                    continue;
                }
        
                if (sourceDep.isComposition() && mergeCompositionChildrenCustom(source, sourceDep))
                    continue;
        
                deleteFutureReflexiveLinks(source, sourceDep);
        
                // Just move the content to the new object
                List<MObject> origDepContent = source.mGet(sourceDep);
                List<MObject> targetDepContent = this.target.mGet(sourceDep);
                for (MObject value : new ArrayList<>(origDepContent)) {
        
                    // Usually the following line is not needed.
                    origDepContent.remove(value);
        
                    targetDepContent.add(value);
        
                    assert (! source.mGet(sourceDep).contains(value));
                    assert (  this.target.mGet(sourceDep).contains(value));
                    assert (sourceDepSym==null || value.mGet(sourceDepSym).contains(this.target)) : String.format("%s.%s does not contain %s", value, sourceDepSym, this.target);
                    assert (sourceDepSym==null || !value.mGet(sourceDepSym).contains(source)): String.format("%s.%s still contains %s", value, sourceDepSym, source);
                }
            } else if (sourceDep.isCompositionOpposite()) {
                // don't move the source object toward the target owner.
                // do nothing, the source object will be deleted
            } else {
                deleteFutureReflexiveLinks(source, sourceDep);
        
                // Move the content to the new object
                // while ensuring order is preserved on the opposite association
                List<MObject> origDepContent = source.mGet(sourceDep);
                for (MObject value : new ArrayList<>(origDepContent)) {
                    moveNonNavigableDepValue(source, sourceDep, origDepContent, value);
                }
            }
        }
        
        // invariant: The original object should be empty
        assert (source.getCompositionChildren().isEmpty()) : source + " still owns:"+source.getCompositionChildren();
        
        String msg = String.format("  Merged %s into %s.",source, this.target);
        
        // Delete the original object
        source.delete();
        
        // Log the action
        //getReport().getLogger().println(msg);
        Log.trace(msg);
        
    }

    @objid ("2bb22d3f-978e-432e-869f-d1bf64c2a8e6")
    private boolean mergeCompositionChildrenCustom(MObject source, SmDependency sourceDep) {
        Class<? extends MObject> i = sourceDep.getTarget().getJavaInterface();
        CustomMerger<? extends MObject> comparator = mergersMap.get(i);
        if (comparator == null )
            return false;
        
        List<MObject> sourceDepContent = source.mGet(sourceDep);
        List<MObject> targetDepContent = this.target.mGet(sourceDep);
        for (MObject sourceValue : new ArrayList<>(sourceDepContent)) {
            // first try to find a match of sourceValue into target.
            // if it is the case merge the sourceValue into the found value in targetDepContent.
            if (targetDepContent.stream().noneMatch(targetVal -> comparator.run(targetVal, sourceValue)
                    && recurseMerge(targetVal, sourceValue))) {
                // No matching object in target, move it from source to target.
        
                // Usually the following line is not needed.
                sourceDepContent.remove(sourceValue);
        
                targetDepContent.add(sourceValue);
            }
        }
        return true;
    }

    /**
     * Merge source into target with a new MergeMachine with the same configuration as this machine.
     * @param targetVal the target to merge into
     * @param sourceValue the source element that will be deleted
     * @return always true, allows to be chained in binary expression.
     */
    @objid ("80ffbbc6-2ff0-4a9f-8ece-344662cf0cd9")
    private boolean recurseMerge(MObject targetVal, MObject sourceValue) {
        new MergeMachine(targetVal)
        .setDeleteNewReflexiveLinks(this.deleteNewReflexiveLinks)
        .setReplaceAttributes(this.replaceAttributes)
        .addSource(sourceValue)
        .merge();
        return true;
    }

    @objid ("f5d57df9-ad95-43a3-8e9a-d1e7adb212e9")
    private static Map<Class<? extends MObject>, CustomMerger<? extends MObject>> initCustomMergers() {
        mergersMap = new HashMap<>();
        addToMap(TaggedValue.class, MergeMachine::isSameTaggedValue);
        addToMap(TagParameter.class, MergeMachine::isSameTagParameter);
        addToMap(PropertyTable.class, MergeMachine::isSamePropertyTable);
        addToMap(TypedPropertyTable.class, MergeMachine::isSameTypedPropertyTable);
        return mergersMap;
    }

    @objid ("2dcb432d-ec38-49c5-9aad-153fc8f44492")
    private static <T extends MObject> void addToMap(Class<T> mc, CustomMerger<T> comparator) {
        mergersMap.put(mc, comparator);
    }

    @objid ("965228c3-cc74-4e7f-9641-999303c1377f")
    private static boolean isSameTaggedValue(TaggedValue target, TaggedValue source) {
        if (Objects.equals(target.getDefinition(), source.getDefinition())) {
            // append tag parameters only if tag with many parameters
            if (target.getDefinition().getParamNumber().equals("1")) {
                new ArrayList<>(source.getActual()).forEach(o -> o.delete());
            }
        
            return true;
        }
        return false;
    }

    @objid ("55ba39b1-04f2-4e29-a567-fc8f6d5f0a67")
    private static boolean isSameTagParameter(TagParameter target, TagParameter source) {
        return Objects.equals(target.getValue(), source.getValue());
    }

    @objid ("a8dba9d6-ca30-49ab-a6cc-48a1eccc4e2b")
    private static boolean isSameTypedPropertyTable(TypedPropertyTable target, TypedPropertyTable source) {
        if (Objects.equals(target.getType(), source.getType())) {
        
            if (false /*this.replaceAttributes*/) {
                //TODO merge target.getPropertyObject(null);
                for (PropertyDefinition prop : source.getType().getOwned()) {
                    Object val = source.getPropertyObject(prop);
                    target.setPropertyObject(prop, val);
                }
            } else {
                // Copy missing properties from source to target
                for (Entry<Object, Object> prop : source.toProperties().entrySet()) {
                    String key = (String)prop.getKey();
                    if (target.getProperty(key) == null /* || this.replaceAttributes*/) {
                        target.setProperty(key, (String)prop.getValue());
                    }
                }
            }
            return true;
        }
        return false;
    }

    @objid ("789aeccd-7175-4194-8ee8-126880606ab8")
    private static boolean isSamePropertyTable(PropertyTable target, PropertyTable source) {
        if (Objects.equals(target.getName(), source.getName())) {
            // Copy missing properties from source to target
            for (Entry<Object, Object> prop : source.toProperties().entrySet()) {
                String key = (String)prop.getKey();
                if (target.getProperty(key) == null /* || this.replaceAttributes*/) {
                    target.setProperty(key, (String)prop.getValue());
                }
            }
        
            return true;
        }
        return false;
    }

    /**
     * Delete link objects that would become reflexive after merging.
     * @param source a source object being merged into this.target .
     * @param sourceDep the scanned dependency.
     */
    @objid ("cf0f1eef-37e4-47bc-9906-748813bc545a")
    private void deleteFutureReflexiveLinks(MObject source, SmDependency sourceDep) {
        if (! this.deleteNewReflexiveLinks)
            return;
        
        SmDependency sourceDepSym = sourceDep.getSymetric();
        Collection<MDependency> linkOppositeDeps = null;
        SmClass depTargetType = sourceDep.getType();
        MExpert mmExpert = depTargetType.getMetamodel().getMExpert();
        if (sourceDepSym.hasDirective(SmDirective.SMCDLINKSOURCE)) {
            linkOppositeDeps = depTargetType.getLinkMetaclassTargets();
        } else if (sourceDepSym.hasDirective(SmDirective.SMCDLINKTARGET)) {
            linkOppositeDeps = depTargetType.getLinkMetaclassSources();
        } else {
            // nothing to do, fast exit.
            return;
        }
        
        // The dep points toward a link object
        // delete all links that once merged will be reflexives.
        
        List<MObject> origDepContent = source.mGet(sourceDep);
        for (MObject linkElement : new ArrayList<>(origDepContent)) {
            boolean noMoreSourceOrTarget = true;
            boolean oppositesWereEmpty = true;
            for (MDependency oppdep : linkOppositeDeps) {
                List<MObject> oppDepContent = linkElement.mGet(oppdep);
                if (oppDepContent.isEmpty()) {
                    continue;
                } else if (oppDepContent.size() == 1 && oppDepContent.contains(this.target)) {
                    oppositesWereEmpty = false;
                    continue;
                } else {
                    oppositesWereEmpty = false;
                    noMoreSourceOrTarget = false;
                }
            }
        
            if (noMoreSourceOrTarget && !oppositesWereEmpty) {
                Log.trace("Merge: Delete %s new reflexive link from %s to %s", linkElement,  mmExpert.getSource(linkElement), mmExpert.getTarget(linkElement));
                linkElement.delete();
            }
        }
        
    }

    @objid ("5abe1b04-5b90-4bdd-8f57-892886db4f63")
    private static boolean isNavigable(SmDependency dep) {
        return dep.isComposition() || dep.isSharedComposition() || dep.isPartOf();
    }

    /**
     * Move the given dependency value to the new object
     * while ensuring order is preserved on the opposite association.
     * @param target the target object
     * @param original the original object
     * @param origDepContent read/write access to the dependency content.
     * @param value the value to move
     */
    @objid ("472b5788-3075-49ff-9590-ded901fba34e")
    private void moveNonNavigableDepValue(MObject original, MDependency dep, List<MObject> origDepContent, MObject value) {
        MDependency origDepOpposite = dep.getSymetric();
        List<MObject> origOppositeContent = value.mGet(origDepOpposite);
        
        int idx = origOppositeContent.indexOf(original);
        if (idx != -1) {
            origDepContent.remove(value);
        
            // Usually the following line is not needed.
            origOppositeContent.remove(original);
            if(!origOppositeContent.contains(this.target)) {
                origOppositeContent.add(idx, this.target);
            }
        
        } else {
            //getReport().getLogger().format("  Warn: %1$s.%2$s contains %3$s but %3$s.%4$s does not contain %1$s.\n",
            //        original, origDep.getName(), value, foundopposites);
            Log.warning("  Warn: %1$s.%2$s contains %3$s but %3$s.%4$s does not contain %1$s, only %5$s.",
                    original, dep.getName(), value, origDepOpposite, origOppositeContent);
        }
        
    }

    @objid ("bcaac413-0f8c-44ef-867b-a0766814c127")
    interface CustomMerger<T extends MObject> {
        @objid ("bf65c1f3-bb22-4baa-8ee9-352d3481bba9")
        @SuppressWarnings ("unchecked")
        default boolean run(MObject target, MObject source) {
            return merge((T)target, (T)source);
        }

        @objid ("684b0e5a-ae93-42f8-ae50-0fff82b507fe")
        boolean merge(T target, T source);
}
    

}
