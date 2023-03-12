//
//  PokedexApp.swift
//  Pokedex
//
//  Created by Mohamed Ben Rejeb on 11/3/2023.
//

import SwiftUI
import shared


@main
struct PokedexApp: App {
    @Environment(\.scenePhase) private var scenePhase
    
    private let lifecycle = LifecycleRegistry()
    
    var body: some Scene {
        WindowGroup {
            GeometryReader { geo in
                ComposeViewControllerToSwiftUI(
                    topSafeArea: Float(geo.safeAreaInsets.top),
                    bottomSafeArea: Float(geo.safeAreaInsets.bottom)
                )
                .ignoresSafeArea()
                .onTapGesture {
                    // Hide keyboard on tap outside of TextField
                    UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                }
                .onChange(of: scenePhase) { phase in
                    switch phase {
                        case .active:
                            print(">> your code is here on scene become active")
                        case .inactive:
                            print(">> your code is here on scene become inactive")
                        case .background:
                            print(">> your code is here on scene go background")
                        default:
                            print(">> do something else in future")
                    }
                }
            }
        }
    }
}

/*
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
}*/
