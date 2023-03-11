//
//  ComposeViewControllerToSwiftUi.swift
//  Pokedex
//
//  Created by Mohamed Ben Rejeb on 11/3/2023.
//

import SwiftUI
import shared

struct ComposeViewControllerToSwiftUI: UIViewControllerRepresentable {
    private let topSafeArea: Float
    private let bottomSafeArea: Float
    
    init(topSafeArea: Float, bottomSafeArea: Float) {
        self.topSafeArea = topSafeArea
        self.bottomSafeArea = bottomSafeArea
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        return Main_iosKt.MainViewController(
            topSafeArea: topSafeArea,
            bottomSafeArea: bottomSafeArea
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
