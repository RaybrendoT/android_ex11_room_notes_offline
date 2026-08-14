# Fix KSP Plugin Unknown Exception

The project is failing to sync because the KSP plugin is applied in the `:app` module without a version, and it is not defined in the root `build.gradle.kts` or the Version Catalog (`libs.versions.toml`).

## Proposed Changes

### Build Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/raybr/AndroidStudioProjects/android_ex11_room_notes_offline/gradle/libs.versions.toml)
- Add KSP version `2.2.10-2.0.2` to the `[versions]` block.
- Add KSP plugin definition to the `[plugins]` block.

#### [MODIFY] [build.gradle.kts (root)](file:///C:/Users/raybr/AndroidStudioProjects/android_ex11_room_notes_offline/build.gradle.kts)
- Add the KSP plugin to the `plugins` block with `apply false`.

#### [MODIFY] [build.gradle.kts (:app)](file:///C:/Users/raybr/AndroidStudioProjects/android_ex11_room_notes_offline/app/build.gradle.kts)
- Update the KSP plugin application to use the Version Catalog alias.

## Verification Plan

### Automated Tests
- Run Gradle sync to ensure the plugin is correctly resolved.
- Run `./gradlew assembleDebug` to verify the build completes successfully.
