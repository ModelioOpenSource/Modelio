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
package org.modelio.vcore.smkernel.mapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vbasic.version.VersionedItem;

/**
 * Metamodel version descriptors.
 * <p>
 * Encapsulates a list of {@link VersionedItem} with convenience methods
 * and persistence methods.
 */
@objid ("9067a353-a3af-4e8c-b55e-e8fe32659e24")
public class MetamodelVersionDescriptor implements Iterable<VersionedItem<?>> {
    @objid ("89596db1-ffe5-4db8-8f09-81ad0fef401e")
    private final Map<String, VersionedItem<?>> content;

    /**
     * Initialize an empty descriptor.
     */
    @objid ("97118bf2-9f48-450f-b0fa-3413553b5da1")
    public  MetamodelVersionDescriptor() {
        this.content = new TreeMap<>();
    }

    /**
     * Builds a set from a version.
     * @param v a version.
     */
    @objid ("e612a835-21cc-4e4c-9d63-5d7b9ec81fa1")
    public  MetamodelVersionDescriptor(VersionedItem<?> v) {
        this.content = new TreeMap<>();
        this.content.put(v.getName(), v);
        
    }

    /**
     * Read a collection of version descriptors written by write()
     * @param is the input reader
     * @throws IOException in case of I/O error.
     */
    @objid ("3bb32d7a-04c1-4abf-81b6-13473c7985e8")
    public  MetamodelVersionDescriptor(Reader is) throws IOException {
        this();
        
        BufferedReader r = new BufferedReader(is);
        
        String s = r.readLine();
        while (s != null) {
            String mmName = s;
            String mmVersion = r.readLine();
        
            put(mmName, new Version(mmVersion));
        
            s = r.readLine();
        }
        
    }

    /**
     * copy constructor.
     * @param other the descriptor to copy.
     */
    @objid ("e80d3b29-e055-41ba-9fdc-9e4d77ba9892")
    public  MetamodelVersionDescriptor(MetamodelVersionDescriptor other) {
        this.content = new TreeMap<>(other.content);
    }

    /**
     * Add a descriptor.
     * @see #put(String, Version)
     * @param d a descriptor.
     * @return this instance to chain calls.
     */
    @objid ("12496777-c70b-44b3-afaf-4190b2c226bf")
    public MetamodelVersionDescriptor addDescriptor(VersionedItem<?> d) {
        this.content.put(d.getName(), d);
        return this;
    }

    /**
     * @param mmname a metamodel name
     * @param min minimum version, included
     * @param max maximum version , excluded
     * @return whether this descriptor contains the given metamodel in the version bounds.
     */
    @objid ("9569660e-2e6f-46b1-8af9-95911affaa46")
    public boolean contains(String mmname, Version min, Version max) {
        Version v = getVersion(mmname);
        if (v != null) {
            return v.isOlderThan(max) && ! v.isOlderThan(min);
        } else {
            return false;
        }
        
    }

    /**
     * @return a copy of this instance.
     */
    @objid ("d23b811d-e5fb-4cdb-94a5-39ff547d7d99")
    public MetamodelVersionDescriptor copy() {
        return new MetamodelVersionDescriptor(this);
    }

    @objid ("a5909751-7d55-4ad0-8d26-023015b598d0")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MetamodelVersionDescriptor other = (MetamodelVersionDescriptor) obj;
        if (this.content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!this.content.equals(other.content)) {
            return false;
        }
        return true;
    }

    /**
     * Keep only elements that satisfy the given test.
     * @param test a test matching elements to keep
     * @return this modified instance.
     */
    @objid ("5a7eb0e0-bf8b-41da-963c-119857835daa")
    public MetamodelVersionDescriptor filter(Predicate<VersionedItem<?>> test) {
        for (Iterator<Entry<String, VersionedItem<?>>> it = this.content.entrySet().iterator(); it.hasNext();) {
            Entry<String, VersionedItem<?>> entry = it.next();
            if (! test.test(entry.getValue())) {
                it.remove();
            }
        }
        return this;
    }

    /**
     * Get a diagnostic about incompatibilities of the other metamodel relative to this one.
     * <p>
     * Incompatibilities include fragments in the other metamodel but missing in this one
     * and version differences. Fragments in this metamodel but not in the other are ignored.
     * <p>
     * If 'allowBuildCompatible' is true, ignore if own version is newer by build number
     * @param other the other metamodel
     * @param allowBuildCompatible ignore if own version is newer by build number
     * @return the found incompatibilities, empty if no problem.
     */
    @objid ("f8a16c70-da4f-425d-8748-356508a54495")
    public Collection<Difference> getIncompatibilities(MetamodelVersionDescriptor other, boolean allowBuildCompatible) {
        Collection<DiffType> filter = allowBuildCompatible ? 
                EnumSet.of(DiffType.missing, DiffType.newer, DiffType.older) : 
                    EnumSet.of(DiffType.missing, DiffType.newer, DiffType.older, DiffType.olderCompatibleBuild);
        return getDifferencesWith(other, filter);
    }

    /**
     * Get the version for the given metamodel.
     * @param mmname a metamodel name
     * @return its version or -1 if there is no such metamodel.
     */
    @objid ("69b4c4ea-39f2-4434-b6ae-e0ea2403e3a6")
    public Version getVersion(String mmname) {
        VersionedItem<?> item = this.content.get(mmname);
        if (item == null) {
            return null;
        } else {
            return item.getVersion();
        }
        
    }

    /**
     * To make compatibility tests.
     * @param name the metamodel to look for
     * @param minVersion the required minimum version
     * @return whether this descriptor contains the given metamodel with at least the required version.
     */
    @objid ("684bba63-d61f-45d4-87db-9371e3f89b5a")
    public boolean hasAtLeast(String name, Version minVersion) {
        VersionedItem<?> v = this.content.get(name);
        return (v != null && v.getVersion().compareTo(minVersion) >= 0);
    }

    @objid ("f11e52e3-7412-45ee-81d5-64c85da5b3ab")
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.content == null) ? 0 : this.content.hashCode());
        return result;
    }

    /**
     * Tells whether the metamodel is "build compatible" with the other metamodel.
     * <p>
     * This metamodel is "build" compatible if the only differences are that the build numbers are
     * more recent in this current metamodel, or this metamodel has more fragments than the other.
     * @param requirements the metamodel requirements
     * @param allowBuildCompatible whether to allow build compatible
     * @return whether the metamodel is "build compatible" with the other metamodel.
     */
    @objid ("b6a79cff-9e99-4238-b64f-a07549d58539")
    public boolean isCompatibleWith(MetamodelVersionDescriptor requirements, boolean allowBuildCompatible) {
        if (! isSame(requirements)) {
            for (VersionedItem<?> neededFrag : requirements) {
                DiffType compat = getRequiredVersionCompatibility(neededFrag);
                if (compat != DiffType.same && !(allowBuildCompatible && compat == DiffType.olderCompatibleBuild)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Test whether the descriptor does not contain anything.
     * @return <i>true</i> if the descriptor is empty.
     */
    @objid ("f0fa6ce4-a4b4-445c-820a-b8f73e4e0fe2")
    public boolean isEmpty() {
        return this.content.isEmpty();
    }

    /**
     * Tells whether this set is the same as the given one.
     * @param other another version set.
     * @return <i>true</i> only if they contain the same versions.
     */
    @objid ("8dfe6a9e-6f5b-4ed9-abb2-e8b3bd057b75")
    public boolean isSame(MetamodelVersionDescriptor other) {
        return this.content.equals(other.content);
    }

    @objid ("d884fded-1b62-493d-8d88-43dea5ae9c38")
    @Override
    public Iterator<VersionedItem<?>> iterator() {
        return this.content.values().iterator();
    }

    /**
     * @param mmName the metamodel fragment name
     * @param v the version
     * @return this instance
     */
    @objid ("0863fcd4-a655-40ac-8bef-f0afa0598c27")
    public MetamodelVersionDescriptor put(String mmName, Version v) {
        this.content.put(mmName, new VersionedItem<>(mmName, v));
        return this;
    }

    @objid ("d43886fa-2013-4ac9-b2e9-625761235678")
    @Override
    public String toString() {
        return this.content.values()
                                                                                        .stream()
                                                                                        .map(VersionedItem::toString)
                                                                                        .collect(Collectors.joining(", "));
    }

    /**
     * @return a non modifiable version of this metamodel descriptor.
     */
    @objid ("ddfde496-fcb1-49d0-b3e7-0e19f3244355")
    public MetamodelVersionDescriptor unmodifiable() {
        return new MetamodelVersionDescriptor(Collections.unmodifiableMap(this.content));
    }

    /**
     * Write the versions to a java {@link Writer}.
     * @param out the target writer
     * @throws IOException in case of I/O error
     */
    @objid ("228b03cf-634a-4ec8-8905-342a245bb296")
    public void write(Writer out) throws IOException {
        for (VersionedItem<?> v : this.content.values()) {
            out.append(v.getName());
            out.append("\n");
            out.append(String.valueOf(v.getVersion()));
            out.append("\n");
        }
        
    }

    /**
     * Internal constructor.
     * @param aContent the map to directly use.
     */
    @objid ("850ce6e0-fa6b-429d-abbe-5279149acb60")
    protected  MetamodelVersionDescriptor(Map<String, VersionedItem<?>> aContent) {
        this.content = aContent;
    }

    /**
     * Compares the given fragment version descriptor with this metamodel and
     * gives the compatibility state of the requirement.
     * @param fragVersionRequirement a metamodel fragment version descriptor.
     * @return the compatibility of the given fragment version relative to this descriptor.
     */
    @objid ("465f503a-228d-4809-b815-14fe1e7487b7")
    protected DiffType getRequiredVersionCompatibility(VersionedItem<?> fragVersionRequirement) {
        Version curVersion = getVersion(fragVersionRequirement.getName());
        Version requirementVersion = fragVersionRequirement.getVersion();
        if (curVersion == null) {
            return DiffType.missing;
            //throw new IOException(CoreProject.getMessage("AbstractFragment.MissingMetamodelFragment", getId(), neededMmFragment, curVersion));
        } else if (curVersion.isOlderThan(requirementVersion)) {
            //throw new IOException(CoreProject.getMessage("AbstractFragment.FutureMmVersion", getId(), neededMmFragment, curVersion));
            return DiffType.newer;
        } else if (curVersion.equals(fragVersionRequirement.getVersion())) {
            return DiffType.same;
        } else if (curVersion.isNewerBuildOf(fragVersionRequirement.getVersion())) {
            return DiffType.olderCompatibleBuild;
        } else {
            // Current version is newer by major or minor version.
            //throw new IOException(CoreProject.getMessage("AbstractFragment.MmVersionNotSupported", getId(), neededMmFragment, curVersion));
            return DiffType.older;
        }
        
    }

    /**
     * Tells whether the needed version is "build" compatible with the current metamodel.
     * <p>
     * A version is "build" compatible if the only difference is that the build number is
     * more recent in the current metamodel.
     * @param neededFragment the needed metamodel fragment with its version
     * @param curVersion the available version
     * @return <i>true</i> if the fragment is build compatible else <i>false</i>.
     */
    @objid ("62bf528f-defe-44b3-bea9-9b42ed46c334")
    private static boolean isBuildCompatible(Version neededVersion, Version curVersion) {
        return (curVersion.getMajorVersion() == neededVersion.getMajorVersion() &&
                                                                        curVersion.getMinorVersion() == neededVersion.getMinorVersion() &&
                                                                        curVersion.getBuildVersion() >= neededVersion.getBuildVersion());
    }

    /**
     * Compare this metamodel versions with another one and get a differences report.
     * <p>
     * Incompatibilities include fragments in the other metamodel but missing in this one
     * and version differences. Fragments in this metamodel but not in the other are ignored.
     * <p>
     * The differences
     * @param other the other metamodel
     * @param filter the wanted differences type
     * @return the found incompatibilities, empty if none matching the filter.
     */
    @objid ("7faaff31-ef76-478b-b506-99b5320819d3")
    public Collection<Difference> getDifferencesWith(MetamodelVersionDescriptor other, Collection<DiffType> filter) {
        Collection<Difference> ret = null;
        
        if (! isSame(other)) {
            for (VersionedItem<?> neededFrag : other) {
                DiffType compat = getRequiredVersionCompatibility(neededFrag);
                if (filter.contains(compat)) {
                    if (ret == null) {
                        ret = new ArrayList<>(other.content.size());
                    }
                    ret.add(new Difference(neededFrag, compat));
                }
            }
        }
        
        if (ret == null) {
            ret = Collections.emptyList();
        }
        return ret;
    }

    /**
     * Compatibility diagnostic
     */
    @objid ("88279d2c-f8fb-45ce-9584-31793b497a7e")
    public enum DiffType {
        /**
         * Same version, no problem
         */
        @objid ("b8ca4199-1cb0-40d1-ab59-5da37bba6e99")
        same,
        /**
         * no problem: the other version is only lower by build number.
         */
        @objid ("d632a898-ea7a-4e6e-88a1-71ec1abfb6c2")
        olderCompatibleBuild,
        /**
         * This metamodel misses a fragment.
         */
        @objid ("944f858a-df6b-4bdd-ab97-d466097c21e1")
        missing,
        /**
         * The other metamodel has a fragment with a newer version.
         */
        @objid ("e5e49c5a-131f-4cd2-8afa-ce1d89290779")
        newer,
        /**
         * The other metamodel has a fragment with a version too old:
         * its minor or major version is older.
         */
        @objid ("d608f238-a998-4ae1-8b40-21f27cce671a")
        older;

    }

    /**
     * We really miss C++ std::pair<A,B> ...
     */
    @objid ("bf5e3d1e-a975-4122-9597-fa078a753660")
    @SuppressWarnings("javadoc")
    public static class Difference {
        @objid ("ee5672c8-6c78-4060-b272-f319bc32bdcb")
        public final DiffType type;

        @objid ("cb24522a-8e17-4ade-8c1d-a25940581388")
        public final VersionedItem<?> neededMmFragment;

        @objid ("875165a8-0f27-49e7-bab5-759d251fda32")
        public  Difference(VersionedItem<?> neededMmFragment, DiffType compat) {
            this.neededMmFragment = neededMmFragment;
            this.type = compat;
            
        }

        @objid ("e7baaaad-2489-4aa1-8d35-fb84f2d8cd30")
        @Override
        public String toString() {
            return this.neededMmFragment+" "+this.type;
        }

    }

}
