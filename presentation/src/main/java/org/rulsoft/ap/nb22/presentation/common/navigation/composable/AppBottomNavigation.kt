package org.rulsoft.ap.nb22.presentation.common.navigation.composable

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import org.rulsoft.ap.nb22.presentation.R
import org.rulsoft.ap.nb22.presentation.common.navigation.NavItem

@Composable
fun AppBottomNavigation(
    bottomNavOptions: List<NavItem>,
    currentDestination: NavDestination?,
    onNavItemClick: (NavItem) -> Unit
) {
    NB22BottomNavigation {
        bottomNavOptions.forEach { item ->
            val title = stringResource(id = item.label?: R.string.app_name)
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(item.navCommand.route::class)
                } == true,
                onClick = { onNavItemClick(item) },
                icon = {
                    if (item.icon != null) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = title
                        )
                    }
                },
                label = { Text(text = title) }
            )
        }
    }
}