Shape Icon View
================
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)

Custom shaped icon view.

![image](https://github.com/LinerSRT/ShapeIconView/blob/master/example.jpg)

Compatibility
-------------

This library is compatible from API 15 (Android 4.0.3).

Usage
-----

From XML:
```xml
<com.liner.shapeiconview.view.ShapeIconView
            android:id="@+id/shapeIconView"
            android:layout_width="48dp"
            android:layout_height="48dp"
            app:siv_color="@color/colorRed"
            app:siv_icon="@drawable/ic_baseline_attach_money_24"
            app:siv_icon_size="small"
            app:siv_shape="super_ellipse" />
```
From code:
```java
        ShapeIconView shapeIconView = new ShapeIconView(this/*context*/);
        shapeIconView.setIcon(ContextCompat.getDrawable(this/*context*/, R.drawable.ic_baseline_attach_money_24));
        shapeIconView.setIconSize(ShapeIconView.IconSize.SMALL);
        shapeIconView.setShape(ShapeIconView.Shape.SUPER_ELLIPSE);
        shapeIconView.setShapeColor(ContextCompat.getColor(this/*context*/, R.color.colorRed));
```

Attributes
-----
|  Attribute  | Description  |
| :------------: | :------------: |
|  **siv_color** |  Background shape color |
|  **siv_shadow_color** |  Shadow color  |
|  **siv_shadow_radius**  |  Shadow radius |
|  **siv_shadow_size**  |  Shadow size |
|  **siv_icon** |  Displayed icon  |
|  **siv_icon_size**  |  Size of displayed icon  |
|  **siv_shape** | Background shape type  |

Methods
-----
|  Method  | Description  |
| :------------: | :------------: |
|  **setShape** |  Set background shape |
|  **setShapeColor** |  Set background shape color |
|  **setIcon** |  Set displayed icon |
|  **setIconSize** |  Set displayed icon  size|

See [sample](https://github.com/LinerSRT/ShapeIconView/tree/master/app) project for more information.