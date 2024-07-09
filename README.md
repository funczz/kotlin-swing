# kotlin-swing

Swingの機能を拡張する

## Using Gradle

In your build.gradle.kts.

```
repositories {
    maven { setUrl("https://funczz.github.io/kotlin-swing") }
}
dependencies {
    implementation("com.github.funczz:swing:<VERSION>")
}
```

## Demo

```console
./gradlew run -DmainClass=<DEMO_CLASS>
```

Demo class:

* AbstractFutureReadableTableModelDemo
* AlternateRowBackgroundJTableDemo
* BgImagePainterDemo
* CharAnimatedIconLabelDemo
* CircleAnimatedIconLabelDemo
* FunctionalActionDemo
* FunctionalDocumentListenerDemo
* FunctionalMouseAdapterDemo
* FunctionalSwingWorkerDemo
* GridBagTableLayoutDemo
* JComponentListCellRendererDemo
* JPanelListDemo
* JTableHeaderDemo
* JTableHeaderWithoutJScrollPaneDemo
* TableRowSorterDemo
* TextAnimatedIconLabelDemo
* ToastDemo
