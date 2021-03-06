/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package com.android.tools.build.bundletool.commands;

import com.android.bundle.Config.BundleConfig;
import com.android.bundle.Config.SuffixStripping;
import com.android.tools.build.bundletool.model.OptimizationDimension;
import com.android.tools.build.bundletool.model.version.Version;
import com.android.tools.build.bundletool.optimizations.OptimizationsMerger;
import com.google.common.collect.ImmutableMap;
import dagger.Module;
import dagger.Provides;

/** Dagger module exposing the BundleConfig. */
@Module
public final class BundleConfigModule {

  @CommandScoped
  @Provides
  static Version provideBundletoolVersion(BundleConfig bundleConfig) {
    return Version.of(bundleConfig.getBundletool().getVersion());
  }

  @CommandScoped
  @Provides
  static ImmutableMap<OptimizationDimension, SuffixStripping>
      provideSuffixStrippingPerOptimizationDimension(BundleConfig bundleConfig) {
    return OptimizationsMerger.getSuffixStrippings(
        bundleConfig.getOptimizations().getSplitsConfig().getSplitDimensionList());
  }

  private BundleConfigModule() {}
}
