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

package org.modelio.gproject.fragment.migration;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.gproject.fragment.FragmentAuthenticationException;
import org.modelio.vbasic.progress.IModelioProgress;
import org.modelio.vbasic.progress.SubProgress;
import org.modelio.vcore.model.spi.mm.IMigrationReporter;
import org.modelio.vcore.model.spi.mm.IMigrationStepDescription;

/**
 * API for SVN fragment migrators.
 */
@objid ("54484a75-ecba-47ae-842f-c0071c787258")
public interface IFragmentMigrator {
    /**
     * Run the migration
     * 
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @param reporter an object to report migration process and result to.
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException on authentication error
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
     */
    @objid ("26d5e2ea-e12c-4cda-804c-7c5b1acf5903")
    default void run(IModelioProgress monitor, IMigrationReporter reporter) throws FragmentAuthenticationException, MigrationFailedException {
        SubProgress mon = SubProgress.convert(monitor, 3);
        try(IMigrationProcess proc = start(mon.newChild(1), reporter);) {
            proc.migrateModel(mon.newChild(1));
            proc.finish(mon.newChild(1));
        }
    }

    /**
     * Get an optional a detailed message to warn to the user telling what he needs to do.
     * 
     * @return a migration detail message.
     */
    @objid ("cf5e28af-ac8f-4ce9-a3a9-503c1144b005")
    String getRequiredUserActions();

    /**
     * Start a migration process on which you have some control.
     * <p>
     * See {@link IMigrationProcess} documentation for usage.
     * 
     * @param monitor the progress monitor to use for reporting progress to the user.
     * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
     * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
     * @param reporter an object to report migration process and result to.
     * @return a migration process handle.
     * @throws org.modelio.gproject.fragment.FragmentAuthenticationException on authentication error
     * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
     * @since 3.7
     */
    @objid ("7879e65d-7eaa-4527-96d5-5f7fff332fd8")
    IMigrationProcess start(IModelioProgress monitor, IMigrationReporter reporter) throws FragmentAuthenticationException, MigrationFailedException;

    /**
     * @return the description of the migration steps.
     * @since 3.7.1
     */
    @objid ("2324cb7a-cc22-492b-8684-1630107dc3c7")
    List<IMigrationStepDescription> getStepsDescription();

    /**
     * Migration process handle on which the caller has some control.
     * <p>
     * This interface extends {@link AutoCloseable} so must be used in a <i>try-with-resources</i> statement.
     * Methods must be called in the following order:
     * <ol>
     * <li>{@link #migrateModel(IModelioProgress)} to migrate the model objects.
     * When this method return the fragment may be mount.
     * <li>either <ul>
     * <li>{@link #finish(IModelioProgress)} to finish and commit migration
     * <li>or {@link #abort(IModelioProgress)} to abort the migration.
     * </ul>
     * <li>{@link #close()} to close the process.
     * <p>
     * If {@link #finish(IModelioProgress)} has not been called before {@link #close()},
     * the implementation may completely cancel the migration of the fragment and restore it to its previous version, by calling {@link #abort(IModelioProgress)}
     * You don't need to call this method if you are using this interface in a <i>try-with-resource</i> statement.
     * </ol>
     * @author cma
     * @since 3.7
     */
    @objid ("595c86aa-41d5-418e-b389-d9d5831799f2")
    interface IMigrationProcess extends AutoCloseable {
        /**
         * Migrate the model objects.
         * <p>
         * When this method return the fragment may be mount.
         * @throws FragmentAuthenticationException
         * 
         * @param monitor the progress monitor to use for reporting progress to the user.
         * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
         * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
         * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
         */
        @objid ("a6a4a5dc-6c5d-4531-a342-a34c0fb2dae1")
        void migrateModel(IModelioProgress monitor) throws MigrationFailedException, FragmentAuthenticationException;

        /**
         * Finish and commit the migration process.
         * 
         * @param monitor the progress monitor to use for reporting progress to the user.
         * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
         * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
         * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
         */
        @objid ("5dd17a9f-37c0-4de1-a13c-dc84ab8621d9")
        void finish(IModelioProgress monitor) throws MigrationFailedException;

        /**
         * Abort the migration process.
         * <p>
         * The implementation should completely cancel the migration of the fragment and restore it to its previous version.
         * 
         * @param monitor the progress monitor to use for reporting progress to the user.
         * It is the caller's responsibility to call {@link IModelioProgress#done() done()} on the given monitor.
         * Accepts <i>null</i>, indicating that no progress should be reported and that the operation cannot be cancelled.
         * @throws org.modelio.gproject.fragment.migration.MigrationFailedException on failure
         */
        @objid ("707da188-6258-4b91-baf1-1c30ecdabed9")
        void abort(IModelioProgress monitor) throws MigrationFailedException;

        /**
         * Release any allocated temporary resource, files, directories not already released by the
         * terminal {@link #finish(IModelioProgress)} or {@link #abort(IModelioProgress)} operations.
         * <p>
         * Long running cleaning should better be implemented in either terminal method
         * <p>
         * The implementation should however call {@link #abort(IModelioProgress)} if
         * no terminal operation has been called.
         */
        @objid ("02777abc-a9ba-433c-9d83-85df95e56eba")
        @Override
        void close() throws MigrationFailedException;

    }

}
