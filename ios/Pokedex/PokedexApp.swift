//
//  PokedexApp.swift
//  Pokedex
//
//  Created by Mohamed Ben Rejeb on 11/3/2023.
//

import SwiftUI
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        let mainViewController = Main_iosKt.MainViewController(
            topSafeArea: Float(window?.safeAreaInsets.top ?? 0),
            bottomSafeArea: Float(window?.safeAreaInsets.bottom ?? 0)
        )
        window?.rootViewController = mainViewController
        window?.makeKeyAndVisible()
        return true
    }
}
